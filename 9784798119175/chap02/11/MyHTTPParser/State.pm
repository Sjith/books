package MyHTTPParser::State;
use Moose::Role;
use HTTP::Response;

requires qw(parse next_state);

has 'is_done' => (
    is => 'rw',
    isa => 'Bool',
    default => 0,
);

has 'is_request_done' => (
    is => 'rw',
    isa => 'Bool',
    default => 0,
);

has 'request' => (
    is => 'rw',
    isa => 'HTTP::Request',
);

no Moose::Role;

1;
