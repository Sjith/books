#!/usr/bin/env perl
use strict;
use warnings;
use Prototype::Producer;
use Fruit;

my $apple_producer = Prototype::Producer->new(
    prototype => Fruit->new(name => 'apple')
);

my $banana_producer = Prototype::Producer->new(
    prototype => Fruit->new(name => 'banana')
);

my $apple = $apple_producer->create();
my $banana = $banana_producer->create();
