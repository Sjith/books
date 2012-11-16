package MyObserver2;
use Moose;

with 'Observer';

__PACKAGE__->meta->make_immutable;

no Moose;

sub notify {
    my ($self, $event) = @_;

    print "MyObserver2\n";
}
