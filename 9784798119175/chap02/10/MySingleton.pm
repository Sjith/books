package MySingleton;
use MooseX::Singleton;

__PACKAGE__->meta->make_immutable;

no MooseX::Singleton;

1;
