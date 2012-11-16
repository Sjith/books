package MyHTTPParser;
use Moose;

has 'buffer' => (
    is => 'rw',
    isa => 'Str',
    default => '',
);

has 'state' => (
    is => 'rw',
    does => 'MyHTTPParser::State',
    requied => 1,
    default => sub {
        MyHTTPParser::State::HTTPMethod->new
    },
);

__PACKAGE__->meta->make_immutable;

no Moose;

sub parse {
    my ($self, $chunk) = @_;

    my $buffer = $self->buffer;
    $buffer .= $chunk;

    my $state = $self->state;
    $state->parse(\$buffer);

    $self->buffer($buffer);
    if ($state->is_done) {
        if ($state->is_request_done) {
            return $state->response();
        } else {
            $self->state($state->next_state);
        }
    }

    return ();
}

1;
