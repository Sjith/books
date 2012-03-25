#!/usr/bin/env perl
use strict;
use warnings;

# 9.7
if (1) {
    use Data::Dumper;

    sub data_for_path {
        my $path = shift;
        if (-f $path or -l $path) {
            return undef;
        }
        if (-d $path) {
            my %directory;
            opendir PATH, $path or die "can't opendir $path: $!";
            my @names = readdir PATH;
            closedir PATH;
            for my $name (@names) {
                next if $name eq '.' or $name eq '..';
                $directory{$name} = data_for_path("$path/$name");
            }
            return \%directory;
        }
        warn "$path is neigher a file nor a directory\n";
        return undef;
    }
    #my $directory = data_for_path("..");
    #print Dumper($directory), "\n";

    sub dump_data_for_path {
        my $path = shift;
        my $data = shift;
        if (not defined $data) {
            print "$path\n";
            return;
        }
        my %directory = %$data;
        for (sort keys %directory) {
            dump_data_for_path("$path/$_", $directory{$_});
        }
    }
    dump_data_for_path("..", data_for_path(".."));
}

# 9.6
if (0) {
    sub factorial {
        my $n = shift;
        if ($n <= 1) {
            return 1;
        } else {
            return $n * factorial($n - 1);
        }
    }
    print factorial(5), "\n";
}

# 9.4
if (0) {
    sub ask_monkey_about2 {
        my $name = shift;
        my %has_pineapples = (
            Gilligan => 5,
            Skipper => 7,
            Professor => 1,
            Ginger => 3,
            Mary_Ann => 2,
            Thurston => 10,
            Lovey => 9,
        );
        return $has_pineapples{$name};
    }

    my @castaways = qw(Gilligan Skipper Professor Ginger Mary_Ann Thurston Lovey);
    my @names = 
        map $_->[0],
        sort { $b->[1] <=> $a->[1] }
        map [ $_, ask_monkey_about2($_) ],
        @castaways;
    print join(" ", @names), "\n";

    # ascii sort (case insensitive)
    my @input_data = qw(a B c D e E d C b A);
    my @output_data = 
        map $_->[0],
        sort { $a->[1] cmp $b->[1] }
        map [ $_, "\U$_" ],
        @input_data;
    print join(" ", @output_data), "\n";
}

# 9.3
if (0) {
    sub ask_monkey_about {
        my $name = shift;
        my %has_pineapples = (
            Gilligan => 5,
            Skipper => 7,
            Professor => 1,
            Ginger => 3,
            Mary_Ann => 2,
            Thurston => 10,
            Lovey => 9,
        );
        return $has_pineapples{$name};
    }

    my @castaways = qw(Gilligan Skipper Professor Ginger Mary_Ann Thurston Lovey);
#    my @wasters = sort {
#        ask_monkey_about($b) <=> ask_monkey_about($a)
#    } @castaways;

    my @names_and_pineapples = map {
        [ $_, ask_monkey_about($_) ],
    } @castaways;
    my @sorted_names_and_pineapples = sort {
        $b->[1] <=> $a->[1];
    } @names_and_pineapples;
    my @names = map $_->[0], @sorted_names_and_pineapples;
    print join(" ", @names), "\n";
}

# 9.2
if (0) {
    my @sorted = qw(Gilligan Skipper Professor Ginger Mary_Ann);
    print join(" ", @sorted), "\n";

    my @sorted_positions = sort { $sorted[$a] cmp $sorted[$b] } 0..$#sorted;
    print join(" ", @sorted_positions), "\n";

    my @ranks;
    #@ranks[@sorted_positions] = (0..$#sorted_positions);
    @ranks[@sorted_positions] = (1..@sorted_positions);
    print join(" ", @ranks), "\n";
}

# 9.1
if (0) {
    my @numerically_sorted = sort {
        if ($a < $b) { -1 }
        elsif ($a > $b) { 1 }
        else { 0 }
    } 1,2,4,8,16,32;
    print join(" ", @numerically_sorted), "\n";
}
