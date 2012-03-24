#!/usr/bin/env perl
use strict;
use warnings;

sub main {
    my @files = @ARGV;
    print map { " " x 4, -s $_, "\t$_\n" } grep -s $_ < 1000, @files;
}

&main;
