#!/usr/bin/env perl
use strict;
use warnings;

package MyDate;
use Carp;
use Time::Piece;

sub new {
    my $class = shift;
    my $t = Time::Piece::localtime();
    my $self = {
        day => $t->mday,
        month => $t->mon,
        year => $t->year,
    };
    return bless $self, $class;
}

sub DESTROY {
}

sub AUTOLOAD {
    my $self = shift;
    our $AUTOLOAD;
    (my $module = $AUTOLOAD) =~ s/.*:://s;
    if (exists $self->{$module}) {
        {
            no strict 'refs';
            *{$AUTOLOAD} = sub { $self->{$module} };
        }
        goto &{$AUTOLOAD};
    } else {
        croak "$module not found.\n";
    }
}

package main;

sub main {
    my $myDate = MyDate->new;
    print $myDate->day, "\n";
    print $myDate->month, "\n";
    print $myDate->year, "\n";
    #print $myDate->hour, "\n";
}

&main;
