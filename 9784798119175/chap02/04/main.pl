#!/usr/bin/env perl
use strict;
use warnings;
use CoffeeShop::A;
use CoffeeShop::B;

my $shop_a = CoffeeShop::A->new;
my $shop_b = CoffeeShop::B->new;

my $coffee;

$coffee = $shop_a->create;
$coffee = $shop_b->create;
