#!/usr/bin/env perl
use strict;
use warnings;

package Animal;
sub speak {
    my $class = shift;
    print "a $class goes ", $class->sound, "\n";
}

package Cow;
use base qw(Animal);
sub sound { 'moooo' }

package Horse;
use base qw(Animal);
sub sound { 'neigh' }

package Sheep;
use base qw(Animal);
sub sound { 'baaaah' }

package Mouse;
use base qw(Animal);
sub sound { 'squeak' }
sub speak {
    my $class = shift;
    $class->SUPER::speak;
    print "[but you can barely hear it!]\n";
}

package main;
my @barnyard = ();
while (1) {
    print "enter an animal (empty to finish): ";
    chomp(my $animal = <STDIN>);
    $animal = ucfirst lc $animal;
    last unless $animal =~ /^(Cow|Horse|Sheep|Mouse)$/;
    push @barnyard, $animal;
}
foreach my $beast (@barnyard) {
    $beast->speak;
}
