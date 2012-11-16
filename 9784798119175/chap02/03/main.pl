#!/usr/bin/env perl
use strict;
use warnings;
use Shape::Composite;
use Shape::Circle;
use Shape::Rectangle;

my $circle1 = Shape::Circle->new(radius => 3);
my $circle2 = Shape::Circle->new(radius => 6);
my $circle3 = Shape::Circle->new(radius => 9);

my $rectangle = Shape::Rectangle->new(height => 4, width => 6);
my $square = Shape::Rectangle->new(height => 10, width => 10);

my $composite = Shape::Composite->new;
$composite->shape_add($circle1);
$composite->shape_add($circle2);
$composite->shape_add($circle3);
$composite->shape_add($rectangle);
$composite->shape_add($square);

$composite->resize(0.5);
$composite->print();
