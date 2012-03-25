#!/usr/bin/env perl
use strict;
use warnings;

package LivingCreature;
sub speak {
    my $class = shift;
    if (@_) {
        print "a $class goes '@_'\n";
    } else {
        print "a $class goes ", $class->sound, "\n";
    }
}

package Person;
use base qw(LivingCreature);
sub sound { 'hmmmm' }

package Animal;
use base qw(LivingCreature);
sub sound { die "all Animals should define a sound" }
sub speak {
    my $class = shift;
    die "animals can't talk!" if @_;
    $class->SUPER::speak;
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
Person->speak;
Person->speak("Hello, world!");
