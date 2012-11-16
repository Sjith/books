package MyHTTPParser::State::HTTPMethod;
use Moose;

with 'MyHTTPParser::State';     # requires qw(parse next_state)

__PACKAGE__->meta->make_immutable;

no Moose;

sub parse {
    my ($self, $buffer) = @_;

    # $bufferを消費しつつ、GET /url HTTP/1.0のようなデータを探す。
    # 注: 下記正規表現はサンプルのため、完全ではない
    if ($$buffer =~ s{
        \s*
        (GET|POST|HEAD|PUT|DELETE)
        \s+
        ([^\s+])
        (?:
            \s+
            (HTTP/\d+\.\d+)
        )?
        \s*
        }{}x) {
        my $request = HTTP::Request->new($1, $2);
        $request->protocol($3 || 'HTTP/0.9');
        $self->request($request);
        $self->is_done(1);
    }
}

sub next_state {
    my $self = shift;

    return MyHTTPParser::State::HTTPHeaders->new(
        request => $self->request,
    );
}

1;
