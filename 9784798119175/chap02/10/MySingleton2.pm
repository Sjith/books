package MySingleton2;
use strict;
use warnings;

our $INSTANCE;

sub new {
    my $class = shift;
    bless {}, $class;
}

sub instance {
    my $class = shift;
    return $INSTANCE ||= $class->new;
}

1;
