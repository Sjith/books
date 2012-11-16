#!/usr/bin/env perl
use strict;
use warnings;

my $g = Game::Othello->new(
    player1 => Game::Othello::Human->new,
    player2 => Game::Othello::AI->new(
        strategy => Game::Othello::Strategy::MinMax->new,
    ),
);
$g->run();

# どこかのタイミングで戦略を変更することも可能
$g->player2->strategy(Game::Othello::Strategy::MonteCarlo->new);
