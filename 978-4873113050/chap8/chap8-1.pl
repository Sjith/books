#!/usr/bin/env perl
use strict;
use warnings;

use IO::Tee;
use Time::Piece;

sub main {

    print "Please select output\n";
    print "1) file\n";
    print "2) scalar\n";
    print "3) both\n";
    while (1) {
        print "=> ";

        my $select_number = <STDIN>;
        if ($select_number =~ /^\d+$/ && $select_number >= 1 && $select_number <= 3) {
            my $file = 'chap8-1.out';
            my $scalar = '';

            my $output;
            if ($select_number == 1) {
                open $output, ">", $file or die;
            } elsif ($select_number == 2) {
                open $output, ">", \$scalar or die;
            } else {
                open my $output_file, ">", $file or die;
                open my $output_scalar, ">", \$scalar or die;
                $output = IO::Tee->new($output_file, $output_scalar);
            }

            print $output Time::Piece::localtime()->strftime('%Y-%m-%d %H:%M:%S'), "\n";

            print $scalar if ($scalar);
            last;
        }
    }
}

&main;
