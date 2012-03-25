#!/usr/bin/env perl
use strict;
use warnings;
use File::Basename;

sub xxx {
    my $path = shift;
    my $offset = shift || 0;

    my $basename = basename($path);
    if (-f $path or -l $path) {
        print " " x $offset, $basename, "\n";
        return;
    } elsif (-d $path) {
        my @files = glob("$path/*");
        if (@files) {
            print " " x $offset, "$basename, with contents:\n";
            foreach my $file (@files) {
                xxx($file, $offset + 2);
            }
        } else {
            print " " x $offset, "$basename, an empty directory\n";
        }
        return;
    }
}
xxx("..");
