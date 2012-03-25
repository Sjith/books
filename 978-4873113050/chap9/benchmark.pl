#!/usr/bin/env perl
use strict;
use warnings;
use Benchmark qw(timethese cmpthese);

my $result = timethese(10000, {
    loop1 => sub {
        my $i = 0;
        while ($i < 1500) {
            $i++;
        }
    },
    loop2 => sub {
        my $i = 0;
        while ($i < 1000) {
            $i++;
        }
    },
    loop3 => sub {
        my $i = 0;
        while ($i < 500) {
            $i++;
        }
    },
});

cmpthese($result);
