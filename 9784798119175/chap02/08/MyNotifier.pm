package MyNotifier;
use Moose;

with 'Notifier';

__PACKAGE__->meta->make_immutable;

no Moose;

sub notify_observers {
    my ($self, $event) = @_;
    foreach my $observer ($self->observers) {
        $observer->notify($event);
    }
}

1;
