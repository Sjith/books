package HTTP::RequestHandler::GET;
use Moose;

with 'HTTP::RequestHandler';

no Moose;

sub handle_request {
    my ($self, $request) = @_;

    return () unless $request->method eq 'GET';

    print "GET\n";
    return 1;
}

1;
