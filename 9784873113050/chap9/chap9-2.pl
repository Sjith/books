#!/usr/bin/env perl
use strict;
use warnings;

use Benchmark qw(timethese cmpthese);

my @files = glob "/bin/*";
my $ret = timethese(0, {
    default => sub {
        my @sorted = sort { -s $a <=> -s $b } @files
    },
    schwartz => sub {
        my @sorted =
            map $_->[0],
            sort { $a->[1] <=> $b->[1] }
            map [ $_, -s $_ ],
            @files;
    },
});
cmpthese $ret;
