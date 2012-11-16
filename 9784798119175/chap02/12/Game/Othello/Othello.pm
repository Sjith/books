package Game::Othello;
use Moose;

has 'player1' => (
    is => 'rw',
    isa => 'Game::Othello::Human',
    required => 1,
);

has 'player2' => (
    is => 'rw',
    isa => 'Game::Othello::AI',
    required => 1,
);

__PACKAGE__->meta->make_immutable;

no Moose;

sub run {
    my $self = shift;

    while (...) {
        my $current_player = ...;   # このターンのプレイヤー
        my $move = $current_player->next_move();
        $self->move($move);
    }
}

sub move {
    # 盤上に次の動作を反映する
}

1;
