package MyIterator::LinkedList;
use Moose;

with 'MyIterator';  # requires next()

has 'list' => (
    is => 'rw',
    isa => 'Data::LinkedList',
    required => 1,
);

__PACKAGE__->meta->make_immutable;

no Moose;

sub next {
    my $self = shift;

    my $next = $self->list->next;
    if ($next) {
        return $next->data;
    }

    return ();
}
