#!/usr/bin/env perl
use strict;
use warnings;
use Visitor::LeftToRight;
use Visitor::RightToLeft;

my @list = (1..10);

my $lr = Visitor::LeftToRight->new;
my $rl = Visitor::RightToLeft->new;

$lr->visit(\@list);
$rl->visit(\@list);
