package TinyLogger;
########################################################################

=head1 NAME

TinyLogger.pm - Logging support (for daemontools instead of log4perl logging).

=cut

# 2013-04-11 Dirk Husung  Version 0.1  initial version
########################################################################

use strict;

use constant cLogFatal => 0;
use constant cLogError => 1;
use constant cLogWarn  => 2;
use constant cLogInfo  => 3;
use constant cLogDebug => 4;

#### configuration #####################################################

my $logger;

my $singleton = undef;

my %logLevel = (
  "DEBUG" => cLogDebug,
  "INFO"  => cLogInfo,
  "WARN"  => cLogWarn,
  "ERROR" => cLogError,
  "FATAL" => cLogFatal,
);

########################################################################

=head1 SUBROUTINES

=item new - Create and initialize an object of this class.

  in:
    $this
    %options
  out:
    $singleton

=cut

sub new
{
  my ($this, %options) = @_;

  unless ($singleton) {
    $singleton = {};
    my $class = ref($this) || $this;

    $singleton->{logLevel} = cLogDebug;

    foreach my $key (keys %options) {
      if ($key =~ /logLevel/) {
	my $logLevel = $logLevel{uc $options{$key}} || cLogDebug;
	$singleton->{logLevel} = $logLevel;	
      }
      else {
	$singleton->{$key} = $options{$key};
      }
    }

    bless($singleton, $class);
  }

  return($singleton);
} # new

########################################################################

=item debug - Log debug message

  in:
    $message
  out:
    -

=cut

sub debug
{
  my $self = shift;

  return if ($self->{logLevel} < cLogDebug);

  my $format = shift @_;
  $format =~ /^(.*)$/;
  $format = $1;
  my $message = sprintf($format, @_);
  chomp($message);
  print("DEBUG: $message\n");
} # debug

########################################################################

=item info - Log info message

  in:
    $message
  out:
    -

=cut

sub info 
{
  my $self = shift;

  return if ($self->{logLevel} < cLogInfo);

  my $format = shift @_;
  $format =~ /^(.*)$/;
  $format = $1;
  my $message = sprintf($format, @_);
  chomp($message);
  print("INFO: $message\n");
} # info

########################################################################

=item warn - Log info message

  in:
    $message
  out:
    -

=cut

sub warn 
{
  my $self = shift;

  return if ($self->{logLevel} < cLogWarn);

  my $format = shift @_;
  $format =~ /^(.*)$/;
  $format = $1;
  my $message = sprintf($format, @_);
  chomp($message);
  print("WARN: $message\n");
} # warn

########################################################################

=item error - Log error message

  in:
    $message
  out:
    -

=cut

sub error
{
  my $self = shift;
  my $format = shift @_;
  $format =~ /^(.*)$/;
  $format = $1;
  my $message = sprintf($format, @_);
  chomp($message);
  print("ERROR: $message\n");
} # error

########################################################################

=item fatal - Log fatal message

  in:
    $message
  out:
    -

=cut

sub fatal
{
  my $self = shift;
  my $format = shift @_;
  $format =~ /^(.*)$/;
  $format = $1;
  my $message = sprintf($format, @_);
  chomp($message);
  print("FATAL: $message\n");
} # fatal

########################################################################
1;
