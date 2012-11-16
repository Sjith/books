#!/usr/bin/env perl
use strict;
use warnings;
use FeedParser;
use FeedParser::Parser::RSS;
use FeedParser::Parser::Atom;

my $rss = FeedParser->new(parser => FeedParser::Parser::RSS->new);
my $atom = FeedParser->new(parser => FeedParser::Parser::Atom->new);

print ref $rss, "\n";
print ref $rss->parser, "\n";
print ref $atom, "\n";
print ref $atom->parser, "\n";
