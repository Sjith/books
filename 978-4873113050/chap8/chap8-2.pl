#!/usr/bin/env perl
use strict;
use warnings;

use File::Basename;
use IO::File;

sub usage {
    my $progname = basename $0;
    die "usage: $progname <filename>\n";
}

sub main {
    my $in_filename = shift @ARGV || usage;
    if (-f $in_filename) {
        my %io_files;
        open my $in, "<", $in_filename or die;
        while (my $line = <$in>) {
            my ($name, $count, $fruit) = split /\s+/, $line;
            unless (exists $io_files{$name}) {
                (my $out_filename = $name) =~ s/^(.+):$/lc($1) . ".info"/e;
                $io_files{$name} = IO::File->new($out_filename, 'w');
            }
            print {$io_files{$name}} $line;
        }
        close $in;
        undef %io_files;
    }
}

&main;
