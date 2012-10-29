#!/usr/bin/env perl
use strict;
use warnings;

use Business::ISBN;

my $isbn = Business::ISBN->new($ARGV[0]);
print "ISBN is " . $isbn->as_string . "\n";
#print "Country code: " . $isbn->country_code . "\n";
print "Publisher code: " . $isbn->publisher_code . "\n";
