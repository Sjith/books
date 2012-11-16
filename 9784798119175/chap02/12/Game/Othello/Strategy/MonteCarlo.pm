package Game::Othello::Strategy::MonteCarlo;
use Moose;

with 'Game::Othello::Strategy';

__PACKAGE__->meta->make_immutable;

no Moose;

sub next_move {
    # モンテカルロ法による、次の一手の計算
}

1;
