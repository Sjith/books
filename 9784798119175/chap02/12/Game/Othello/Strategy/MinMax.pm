package Game::Othello::Strategy::MinMax;
use Moose;

with 'Game::Othello::Strategy';

__PACKAGE__->meta->make_immutable;

no Moose;

sub next_move {
    # min-max法による、次の一手の計算
}

1;
