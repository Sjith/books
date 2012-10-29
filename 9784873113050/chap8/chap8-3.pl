#!/usr/bin/env perl
use strict;
use warnings;

use File::Basename;
use IO::Dir;

sub usage {
    my $progname = $0;
    die "usage: $progname <directories>\n";
}

sub main {
    my @directories = @ARGV;
    foreach my $directory (@directories) {
        next unless -d $directory;
        print $directory, "\n";
        my $dir_fh = IO::Dir->new($directory) or die;
        while (defined(my $file = $dir_fh->read)) {
            next if $file =~ /^(?:\.|\.\.)$/;
            print join("\t", "", $file), "\n";
        }
        print "\n";
    }
}

&main;
