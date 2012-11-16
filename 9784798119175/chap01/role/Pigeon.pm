package Pigeon;
use Moose;
extends 'Bird';
with 'Winged';

sub fly { print "Pigeons fly in the day\n" }

1;
