package HTTP::RequestHandler::POST;
use Moose;

with 'HTTP::RequestHandler';

no Moose;

sub handle_request {
    my ($self, $request) = @_;

    return () unless $request->method eq 'POST';

    print "POST\n";
    return 1;
}

1;
