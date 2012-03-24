#!/usr/bin/env perl
use strict;
use warnings;

my $all = "**all machines**";
my %total_bytes;
while (<>) {
    next if /^#/;
    my ($source, $destination, $bytes) = split;
    $total_bytes{$source}{$destination} += $bytes;
    $total_bytes{$source}{$all} += $bytes;
}

my @sources = sort { $total_bytes{$b}{$all} <=> $total_bytes{$a}{$all} } keys %total_bytes;
foreach my $source (@sources) {
    print "$source: $total_bytes{$source}{$all} total bytes sent\n";
    my @destinations = sort { $total_bytes{$source}{$b} <=> $total_bytes{$source}{$a} } keys %{$total_bytes{$source}};
    foreach my $destination (@destinations) {
        next if $destination eq $all;
        print "\t$source => $destination: $total_bytes{$source}{$destination} bytes\n";
    }
}
