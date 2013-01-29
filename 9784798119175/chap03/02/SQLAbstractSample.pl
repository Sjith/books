#!/usr/bin/env perl
use strict;
use warnings;
use SQL::Abstract;

my $sqla = SQL::Abstract->new();

### select
{
    my ($sql, @binds) = $sqla->select(
        'member',
        [ '*' ],
        {
            member_id => 'nobody@example.com',
        }
    );

    my $dbh = DBI->connect(...);
    my $sth = $dbh->prepare($sql);
    $sth->execute(@binds);
    my @rv;
    while (my $h = $sth->fetchrow_hashref) {
        push @rv, $h;
    }
}

### insert
{
    my ($sql, @binds) = $sqla->insert(
        'member',
        {
            member_id => 'nobody@example.com',
            name => 'Daisuke Maki',
        }
    );
}

### update
### - update member set name = 'Daisuke Maki' where member_id = 'nobody@example.com'
{
    my ($sql, @binds) = $sqla->update(
        'member',
        { name => 'Daisuke Maki' },
        { member_id => 'nobody@example.com' }
    );
}

### delete
{
    my ($sql, @binds) = $sqla->delete(
        'member',
        { member_id => 'nobody@example.com' }
    );
}
