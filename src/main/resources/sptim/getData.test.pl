#!/usr/bin/perl

use Getopt::Long;
use strict;
use warnings;

my ($in, $out);

GetOptions(
        "from=s" => \$in,
        "output=s" => \$out
) or die("Params falsch");

open(my $fh, '>', $out);
print $fh "n:1834264:ctt0002\@tuhh.de:ctt0002:qDwBivBd:2\n";
print $fh "u:21155270:ctt0003\@tuhh.de:ctt0003:PjpsHOXE:3\n";
close $fh;

open(my $fh, '>', 'getData.log');
print $fh "This is a washreal test\n";
close $fh;
