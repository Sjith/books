package Prototype::Producer;
use Moose;
use Moose::Util::TypeConstraints;
use Storable qw(dclone);

subtype 'Prototype::Clonable'
    => as 'Object'
    => where { $_->can('clone') }
;

has 'prototype' => (
    is => 'rw',
    isa => 'Prototype::Clonable',
    required => 1,
);

__PACKAGE__->meta->make_immutable;

no Moose;
no Moose::Util::TypeConstraints;

sub create {
    my $self = shift;
    print "clone ", $self->prototype->name, "\n";
    #return $self->prototype->clone();
    return Storable::dclone($self->prototype);
}

1;
