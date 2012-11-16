package Font;
use Moose;

has 'name' => (
    is => 'rw',
    isa => 'Str',
    required => 1,
);

has 'size' => (
    is => 'rw',
    isa => 'Int',
    required => 1,
);

__PACKAGE__->meta->make_immutable;

1;
