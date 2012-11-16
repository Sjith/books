package Game::Othello::AI;
use Moose;

has 'context' => (
    is => 'rw',
    isa => 'Game::Othello',
);

has 'strategy' => (
    is => 'rw',
    does => 'Game::Othello::Strategy',
    required => 1,
    handles => [ 'next_move' ],
);

__PACKAGE__->meta->make_immutable;

no Moose;

1;
