#!/usr/bin/env perl
use strict;
use warnings;

sub check_required_items {
    my $who = shift;
    my $items = shift;
    my @required = qw(preserver sunscreen water_bottle jacket);
    my @missing = ();
    for my $item (@required) {
        unless (grep $item eq $_, @$items) {
            print "$who is missing $item.\n";
            push @missing, $item;
        }
    }
    if (@missing) {
        print "Adding @missing to @$items for $who.\n";
        push @$items, @missing;
    }
}

sub check_items_for_all {
    my $all = shift;
    for my $person (sort keys %$all) {
        check_required_items($person, $all->{$person});
    }
}

sub main {
    my @gilligan = qw(red_shirt hat lucky_socks water_bottle);
    my @professor = qw(sunscreen water_bottle slide_rule batteries radio);
    my @skipper = qw(blue_shirt hat jacket preserver sunscreen);
    my %all = (
        Gilligan => \@gilligan,
        Skipper => \@skipper,
        Professor => \@professor,
    );
    check_items_for_all(\%all);
}

&main;
