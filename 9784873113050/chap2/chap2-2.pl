#!/usr/bin/env perl
use strict;
use warnings;

sub main {
    chdir;
    while (1) {
        print "Please enter a regular expression> ";
        chomp (my $regex = <STDIN>);
        last unless (defined $regex && length $regex);
        print map { "    $_\n" } grep { eval { /$regex/ } } glob(".* *");
    }
}

&main;
