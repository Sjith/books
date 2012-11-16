package Shape::Circle;
use Moose;
use MooseX::AttributeHelpers;

with 'Shape';

has 'radius' => (
    metaclass => 'Number',
    is => 'rw',
    isa => 'Num',
    required => 1,
    provides => {
        mul => 'radius_mul'
    }
);

__PACKAGE__->meta->make_immutable;

no Moose;

sub resize {
    my ($self, $factor) = @_;
    $self->radius_mul($factor);
}

sub print {
    my $self = shift;
    print "Circle: radius = ", $self->radius, "\n";
}

1;
