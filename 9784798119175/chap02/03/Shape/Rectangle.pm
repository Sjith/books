package Shape::Rectangle;
use Moose;
use MooseX::AttributeHelpers;

with 'Shape';

has 'height' => (
    metaclass => 'Number',
    is => 'rw',
    isa => 'Num',
    required => 1,
    provides => {
        mul => 'height_mul',
    },
);

has 'width' => (
    metaclass => 'Number',
    is => 'rw',
    isa => 'Num',
    required => 1,
    provides => {
        mul => 'width_mul',
    },
);

__PACKAGE__->meta->make_immutable;

no Moose;

sub resize {
    my ($self, $factor) = @_;
    $self->height_mul($factor);
    $self->width_mul($factor);
}

sub print {
    my $self = shift;
    print "Rectangle: height = ", $self->height, ", width = ", $self->width, "\n";
}

1;
