#!/usr/bin/perl -wT
########################################################################

=head1 NAME

getData.pl - Retrieve data from given source.

=head1 SYNOPSIS

getData.pl [options]

Options:

    --from=SOURCE  data source from which to retrieve data, e.g "studdata"
    --output=FILE  output file name
    --help         print this help message

Returns:

    0 in case of success,
    1 in case of an error.

=cut

# 2009-02-10 Dirk Husung  Version 0.1  initial version
########################################################################

use lib qw(./);
use strict;
use Getopt::Long;
use Pod::Usage;
use TinyLogger;

# -2       Forces ssh to try protocol version 2 only.
# -a       Disables forwarding of the authentication agent connection.
# -x       Disables X11 forwarding.
# -n     ! Redirects stdin from /dev/null (actually, prevents reading from
#          stdin).
# -T     ! Disable pseudo-tty allocation.
# -q       Quiet mode.
# -e none  Disables any escapes and makes the session fully transparent.

#  sshGet     => "ssh -2 -a -n -x -q -T -e none -o 'BatchMode yes' " .
#                "-l $config{udbUser} $config{udbHost} get_jobqueue.pl",
#  sshAck     => "ssh -2 -a -x -q -T -e none -o 'BatchMode yes' " .
#                "-l $config{udbUser} $config{udbHost} ack_jobq",

#### configuration #####################################################

my $debug = 0;

my $logger;

my %config = ();

$config{user} = "studdata";
$config{host} = "blackhole.rz.tu-harburg.de";

$0 =~ /([^\/\\]+)$/;
$config{script} = $1;
$config{baseDir} = $0;
$config{baseDir} =~ s/$config{script}$//;
$config{baseDir} =~ s/[\/\\]$//;
$config{baseDir} =~ /^(.*)$/;
$config{baseDir} = $1 || ".";
$config{script} =~ /^([^\.]+)/;
$config{script} = $1; # without extensions

%config = (%config,

  ssh => "/usr/bin/ssh",
  sshUser => "$config{user}\@$config{host}",
  sshKey => "~/.ssh/id_rsa_tim",

  shell => "/bin/bash -c",
  gpg => "/usr/bin/gpg",
  gpgHome => "~/gnupg",
  passphrase => "~/gnupg/passphrase",

  tmpFile => "./$config{script}-$$.asc",
  outFile => "./test.txt",
);

my $patternLayout = "%d{yyyy-MM-dd HH:mm:ss} %M(%L) [%p] %m%n";

my %options = ();

########################################################################

=head1 SUBROUTINES

=item filesize

  in:
    $filename
  out:
    -
  return value:
    $filesize
  exceptions:
    -

=cut

sub filesize($)
{
  my $filename = shift;

  return (stat($filename))[7];
} # filesize


########################################################################

=item throw - Log an error and die.

  in:
    $self
    $exception
  out:
    -
  return value:
    -
  exceptions:
    "\n"

=cut

sub throw
{
  my $exception = shift;

  if ($exception) {
    $logger->error($exception);

#   chomp($exception);
#   print STDERR "$exception\n";
  }

  die("\n"); # propagate exception
} # throw

########################################################################

=item gpgDecode - GnuPG decode a file.

  in:
    (
      inFile  - full name of the file to decode
      outFile - full name of the file to which to append the output
    )
  out:
    -
  return value:
    $message - (unprocessed) status message
  exceptions:
    Could not fork "<gpg>"
    "<gpg>" died on signal <signal>
    "<gpg>" returned status code <status code>
    Could not rename file

=cut

sub gpgDecode(@) {
  my %arg = @_;
  my $inFile = $arg{inFile};
  my $outFile = $arg{outFile};

  my $cmd = $config{gpg}.
    " --homedir $config{gpgHome}".
    " --quiet".
    " --pinentry-mode loopback".
    " --batch".
    " --passphrase-file $config{passphrase}".
    " --status-fd 2".
    " --decrypt $inFile";


  $cmd =~ /^(.*)$/;
  $cmd = $1;

  $outFile =~ /^(.*)$/;
  $outFile = $1;


  open(GPG, "$config{shell} \"$cmd >>$outFile\" 2>&1 |")
      or throw("Could not fork \"$config{gpg}\" ($@)");

  my $rc = 0;
  my $message = "";

  my $passphraseOk = 1;
  my $decryptionOk = 0;
  my $mdcOk = 0;

  while(<GPG>) {
    my $stderr = $_;
    if ($stderr =~ /^\[GNUPG:\]\s*(.*)\s*$/) {
      if ($1 =~ /^GOOD_PASSPHRASE$/i) {
        $passphraseOk = 1;
      }
      elsif ($1 =~ /^DECRYPTION_OKAY$/i) {
        $decryptionOk = 1;
      }
      elsif ($1 =~ /^GOODMDC$/i) {
        $mdcOk = 1;
      }
      $message .= $stderr if ($debug);
    }
    else {
      $message .= $stderr;
    }
  }
  close(GPG);

  $rc = $?;
  my $ok = $passphraseOk && $decryptionOk && $mdcOk;

  if ($rc || $message || !$ok) {
    my $exception = "";

    if ($rc & 255) {
      $exception = sprintf("\"$config{gpg}\" died on signal %d", $rc & 255);
    }
    $rc >>= 8;
    if ($rc) {
      $exception .= "\n" if ($exception);
      $exception .= sprintf("\"$config{gpg}\" returned status code %d\n", $rc);
    }
    if ($message) {
      chomp($message);
      $message .= "\n";
    }
    if ($exception) {
      $exception .= "\n" if ($message);
      throw($exception . $message);
    }
    if (!$ok) {
      throw("Decryption failed.\n" . $message);
    }
    return $message;
  }

  return 0;
} # gpgDecode

########################################################################

=item getData  - retrieve data from given source and acknowledge the reception

  in:
    (
      from   - data source from which to retrieve data
      output - output file name
    )

  out:
    -
  return value:
    -
  exceptions:
    Could not fork "<ssh>"
    Could not open "<tmpFile>"
    Could not close "<tmpFile>"
    Could not delete "<tmpFile>"
    "<ssh>" died on signal <signal>
    "<ssh>" returned status code <status code>
    Invalid structure of source data

=cut

sub getData(@)
{
  my %arg = @_;
  my $source = $arg{from};
  my $outFile = $arg{output};

  # retrieve data

  my $cmd = $config{ssh}.
    " -2 -a -n -x -q -T".
    " -e none".
    " -o 'BatchMode yes'".
    " -o 'StrictHostKeyChecking yes'".
    " -i $config{sshKey}".
    " $config{sshUser} read $source";

  my $command = "$cmd 2>&1 |";
# print "$command \n";
  $command =~ /^(.*)$/;
  $command = $1;

  open(CMD, $command)
      or throw("Could not fork \"$config{ssh}\"");

  my @id = ();

  my $state = 0;
  while(<CMD>) {
    my $line = $_;

    if ($state == 0 && $line =~ /^\s*<transmission>\s*$/) {
      $state = 1;
    }
    elsif ($state == 1) {
      if ($line =~ /^\s*<\/transmission>\s*$/) {
        $state = 0;
        last;
      }
      elsif ($line =~ /^\s*<data\s+id\s*=\s*"([^"]+)"\s*>\s*$/) {
        push(@id, $1);
        open(TMPFILE, ">$config{tmpFile}")
            or throw("Could not open \"$config{tmpFile}\"");
        $state = 2;
      }
    }
    elsif ($state == 2) {
      if ($line =~ /^\s*<\/data>\s*$/) {
        close(TMPFILE)
            or throw("Could not close \"$config{tmpFile}\"");

        my $message;
        eval {
          $message = gpgDecode(
            inFile => $config{tmpFile},
            outFile => $outFile);
        };
        if (my $exception = $@) {
          throw($exception) unless ($exception eq "\n");
          throw();
        }
        elsif ($message) {
          $logger->info($message);
#         chomp($message);
#         print STDERR "$message\n";
        }

        unlink($config{tmpFile})
            or throw("Could not delete \"$config{tmpFile}\"");

        $state = 1;
      }
      else {
        print TMPFILE $line;
      }
    }
  }
  if ($state != 0) {
    throw("Invalid structure of source data");
  }

  close(CMD);
  if (my $rc = $?) {
    my $exception;

    if ($rc & 255) {
      $exception = sprintf("\"$config{ssh}\" died on signal %d", $rc & 255);
    }
    $rc >>= 8;
    if ($rc) {
      $exception .= "\n" if ($exception);
      $exception .= sprintf("\"$config{ssh}\" returned status code %d\n", $rc);
    }
    throw($exception);
  }

  # acknowledge the data reception

  $cmd = $config{ssh}.
    " -2 -a -x -q -T".
    " -e none".
    " -o 'BatchMode yes'".
    " -o 'StrictHostKeyChecking yes'".
    " -i $config{sshKey}".
    " $config{sshUser} remove $options{from}";


  $cmd =~ /^(.*)$/;
  $cmd = $1;

  open(CMD, "| $cmd")
    or throw("Cold not fork $config{ssh}\n");

  foreach my $id (@id) {
    print CMD "</data id=\"$id\">\n";
  }

  close(CMD);
  if (my $rc = $?) {
    my $exception;

    if ($rc & 255) {
      $exception = sprintf("\"$config{ssh}\" died on signal %d", $rc & 255);
    }
    $rc >>= 8;
    if ($rc) {
      $exception .= "\n" if ($exception);
      $exception = sprintf("\"$config{ssh}\" returned status code %d\n", $rc);
    }
    throw($exception);
  }
} # getData

#### main ##############################################################

$ENV{PATH} = "";
delete @ENV{qw(IFS CDPATH ENV BASH_ENV)};

my $rc = 0;

select(STDERR); $| = 1;
select(STDOUT); $| = 1;

# handle options

my $noArgs = scalar(@ARGV) == 0;

my $result = GetOptions(\%options, qw(
  from=s
  output=s
  help|h|?),
) || pod2usage(-message => " ", -verbose => 1, -noperldoc => 1,
    -exit_val => 1);

pod2usage(-verbose => 1, -noperldoc => 1, -exit_val => 0)
  if ($options{help} || $noArgs || scalar(@ARGV));

pod2usage(-verbose => 1, -noperldoc => 1, -exit_val => 1)
  unless ($options{from} && $options{output});

$logger = TinyLogger->new();
$logger->info("----- start ----");

# get data

my $message = 0;
eval {

  chomp(my $filename = $options{output});
  $filename =~ /^(.*)$/;
  $filename = $1;

  open(FILE, ">$filename")
      or throw("Could not open \"$filename\"");
  close(FILE);

  $message = getData(%options);
};
if (my $exception = $@) {
  unless ($exception eq "\n") {
    $logger->error($exception);
#   chomp($exception);
#   print STDERR "$exception\n";
  }
  $rc = 1;
}
elsif ($message) {
  $logger->warn($message);
# chomp($message);
# print STDERR "$message\n";
}

$logger->info("---- stop ----");

exit $rc;