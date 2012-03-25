#!/usr/bin/env perl
use strict;
use warnings;

# usage: chap9-3.pl word_list.txt
chomp(my @word_lists = <>);

print join("\n",
    map $_->[0],
    sort { $a->[1] cmp $b->[1] }
    map {
        my $caseinsentive = $_;
        $caseinsentive =~ tr/A-Z/a-z/;
        [$_, $caseinsentive];
    } @word_lists
), "\n";
