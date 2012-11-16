package Shape::Composite;
use Moose;
use MooseX::AttributeHelpers;

with 'Shape';

has 'shapes' => (
    metaclass => 'Collection::Array',
    is => 'rw',
    isa => 'ArrayRef[Shape]',
    auto_deref => 1,
    default => sub { [] },
    provides => {
        push => 'shape_add',
    },
);

__PACKAGE__->meta->make_immutable;

no Moose;

sub resize {
    my ($self, $factor) = @_;
    foreach my $shape ($self->shapes) {
        $shape->resize($factor);
    }
}

sub print {
    my $self = shift;
    foreach my $shape ($self->shapes) {
        $shape->print();
    }
}

1;
