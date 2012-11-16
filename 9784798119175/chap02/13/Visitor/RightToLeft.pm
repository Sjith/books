package Visitor::RightToLeft;
use Moose;
extends 'Data::Visitor';

__PACKAGE__->meta->make_immutable;

no Moose;

sub visit_value {
    print "$_[1]\n";
}

sub visit_array {
    my ($self, $ref) = @_;
    for my $x (reverse @$ref) {
        $self->visit($x);
    }
}

1;
