#!/usr/bin/env perl
use strict;
use warnings;

print join("\n",
    map join(" ", $_->[1], $_->[0]),
    sort { $a->[1] <=> $b->[1] }
    map [ $_, -s $_ ],
    glob "/bin/*"
), "\n";
