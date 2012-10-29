package Oogaboogoo2;
use strict;
use warnings;
use base qw(Exporter);
our @EXPORT_OK = qw(day month);
our %EXPORT_TAGS = (all => [@EXPORT_OK]);

my @day = qw(ark dip wap sen pop sep kir);
my @month = qw(diz pod bod rod sip wax lin sen kun fiz nap dep);

sub day {
    my $number = shift;
    if ($number >= 0 && $number <= 6) {
        return $day[$number];
    } else {
        return undef;
    }
}

sub month {
    my $number = shift;
    if ($number >= 0 && $number <= 11) {
        return $month[$number];
    } else {
        return undef;
    }
}

1;
