package CoffeeShop::B;
use Moose;
use Coffee;
use Coffee::Colombia;

with 'Coffee::Factory';

__PACKAGE__->meta->make_immutable;

no Moose;

sub create {
    return Coffee->new(
        beans => [
            Coffee::Colombia->new,
        ]
    );
}

1;
