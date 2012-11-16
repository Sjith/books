package Bat;
use Moose;
extends 'Mammal';
with 'Winged';

sub fly { print "Bats fly in the night\n" }

1;
