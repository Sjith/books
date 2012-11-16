package MyHTTPParser::State::HTTPHeaders;
use Moose;

with 'MyHTTPParser::State'; # requires qw(parse next_state)

__PACKAGE__->meta->make_immutable;

no Moose;

sub parse {
    my ($self, $buffer) = @_;

    # ヘッダのパース...
    $self->is_done(1);
}

sub next {
    my $self = shift;
    return MyHTTPParser::State::HTTPBody->new(
        request => $self->request,
    );
}

1;
