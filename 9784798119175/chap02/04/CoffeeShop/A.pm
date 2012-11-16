package CoffeeShop::A;
use Moose;
use Coffee;
use Coffee::Guatemala;
use Coffee::Brazil;

with 'Coffee::Factory';

__PACKAGE__->meta->make_immutable;

no Moose;

sub create {
    return Coffee->new(
        beans => [
            Coffee::Guatemala->new,
            Coffee::Brazil->new,
        ]
    );
}

1;
