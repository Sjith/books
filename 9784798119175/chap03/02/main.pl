#!/usr/bin/env perl
use strict;
use warnings;
use DBI;
use MyApp::SQLLibrary;

my $library = MyApp::SQLLibrary->new(file => 'sql.yaml');
my $dbh = DBI->connect(...);

my $sth = $dbh->prepare($library->get_sql('get_member'));
