#!/usr/bin/env perl

package Oogaboogoo::date;
use strict;
use warnings;

my @day = qw(ark dip wap sen pop sep kir);
my @month = qw(diz pod bod rod sip wax lin sen kun fiz nap dep);

sub day {
    my $number = shift;
    if ($number >= 0 && $number <= 6) {
        return $day[$number];
    } else {
        return undef;
    }
}

sub month {
    my $number = shift;
    if ($number >= 0 && $number <= 11) {
        return $month[$number];
    } else {
        return undef;
    }
}

package main;
use strict;
use warnings;

print Oogaboogoo::date::day(0), "\n";
print Oogaboogoo::date::month(0), "\n";
