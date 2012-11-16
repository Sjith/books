package MyIterator::Array;
use Moose;
use MooseX::AttributeHelpers;

with 'MyIterator';

has 'array' => (
    metaclass => 'Collection::Array',
    is => 'rw',
    isa => 'ArrayRef',
    required => 1,
    trigger => sub {
        my ($self, $list) = @_;
        $self->size(scalar @$list);
    },
    provides => {
        get => 'array_get',
    },
);

has 'size' => (
    is => 'rw',
    isa => 'Int',
    default => 0,
);

has 'current' => (
    metaclass => 'Number',
    is => 'rw',
    isa => 'Int',
    default => 0,
    provides => {
        add => 'add_current',
    },
);

__PACKAGE__->meta->make_immutable;

no Moose;

sub next {
    my $self = shift;

    if ($self->current >= $self->size) {
        return ();
    }

    my $i = $self->current;
    $self->add_current(1);
    return $self->array_get($i);
}

1;
