package MyHTTPParser::State::HTTPBody;
use Moose;

with 'MyHTTPParser::State';

__PACKAGE__->meta->make_immutable;

no Moose;

sub parse {
    my ($self, $buffer) = @_;

    # ボディのパース
    my $request = $self->request;

    if (my $length = $request->content_length) {
        if ($length <= bytes::length($request->content)) {
            $self->is_request_done(1);
            $self->is_done(1);
        }
    }
}

sub next_state {
    return ();
}

1;
