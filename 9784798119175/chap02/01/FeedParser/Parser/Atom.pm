package FeedParser::Parser::Atom;
use Moose;

with 'FeedParser::Parser';

__PACKAGE__->meta->make_immutable;

no Moose;

sub parse_file {}
sub parse_handle {}
sub parse_string {}

1;
