package Fruit;
use Moose;
use Scalar::Util qw(blessed);

has 'name' => (
    is => 'rw',
    isa => 'Str',
    required => 1,
);

__PACKAGE__->meta->make_immutable;

no Moose;

sub clone {
    my $self = shift;
    return blessed($self)->new(name => $self->name);
}

1;
