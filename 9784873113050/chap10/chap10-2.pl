#!/usr/bin/env perl

package Oogaboogoo::date;
use strict;
use warnings;

my @wday = qw(ark dip wap sen pop sep kir);
my @month = qw(diz pod bod rod sip wax lin sen kun fiz nap dep);

sub wday_by_number {
    my $number = shift;
    if ($number >= 0 && $number <= 6) {
        return $wday[$number];
    } else {
        return undef;
    }
}

sub month_by_number {
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

my ($sec, $min, $hour, $mday, $month, $year, $wday) = localtime;
print "Today is ", Oogaboogoo::date::wday_by_number($wday),
    ", ", Oogaboogoo::date::month_by_number($month), " $mday, ",
    $year + 1900, "\n";
