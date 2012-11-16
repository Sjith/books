#!/usr/bin/env perl
use strict;
use warnings;
use MyNotifier;
use MyObserver1;
use MyObserver2;

my $target = MyNotifier->new;
$target->add_observer(MyObserver1->new);
$target->add_observer(MyObserver2->new);

$target->notify_observers('event');
