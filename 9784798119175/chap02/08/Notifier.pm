package Notifier;
use Moose::Role;
use MooseX::AttributeHelpers;
use Observer;

requires 'notify_observers';

has 'observers' => (
    metaclass => 'Collection::Array',
    is => 'rw',
    isa => 'ArrayRef[Observer]',
    default => sub { [] },
    auto_deref => 1,
    provides => {
        push => 'add_observer',
    },
);

no Moose::Role;

#sub notify_observers {
#    my ($self, $event) = @_;
#    foreach my $observer ($self->observers) {
#        $observer->notify($event);
#    }
#}

1;
