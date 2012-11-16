#!/usr/bin/env perl
use strict;
use warnings;
use MyIterator::File;

my $i = MyIterator::File->new(file => '/path/to/data.txt');

while (my $x = $i->next) {
    # ...
}
