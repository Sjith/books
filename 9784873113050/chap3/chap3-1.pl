#!/usr/bin/env perl
use strict;
use warnings;

use Cwd;
use File::Spec;

my $cwd = getcwd;
print map { "    " . File::Spec->catfile($cwd, $_) . "\n"} glob(".* *");
