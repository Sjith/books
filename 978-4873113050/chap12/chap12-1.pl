#!/usr/bin/env perl

package Animal;
use strict;
use warnings;
use Carp qw(croak);

sub named {
    ref(my $class = shift) and croak "class name needed";
    my $name = shift;
    my $self = { Name => $name, Color => $class->default_color };
    bless $self, $class;
}

sub default_color { "brown" }

sub sound { croak "subclass must define a sound" }

sub speak {
    my $either = shift;
    print $either->name, " goes ", $either->sound, "\n";
}

sub name {
    my $either = shift;
    ref $either ? $either->{Name} : "an unnamed $either";
}

sub color {
    my $either = shift;
    ref $either ? $either->{Color} : $either->default_color;
}

sub set_name {
    ref(my $self = shift) or croak "instance variable needed";
    $self->{Name} = shift;
}

sub set_color {
    ref(my $self = shift) or croak "instance variable needed";
    $self->{Color} = shift;
}

package Horse;
our @ISA = qw(Animal);
sub sound { "neight" }

package Sheep;
our @ISA = qw(Animal);
sub color { "white" }
sub sound { "baaah" }

package main;
my $tv_horse = Horse->named("Mr. Ed");
$tv_horse->set_name("Mister Ed");
$tv_horse->set_color("grey");
print $tv_horse->name, " is ", $tv_horse->color, "\n";
print Sheep->name, " colored ", Sheep->color, " goes ", Sheep->sound, "\n";
