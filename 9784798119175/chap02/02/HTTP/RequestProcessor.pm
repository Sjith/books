package HTTP::RequestProcessor;
use Moose;
use MooseX::AttributeHelpers;
use HTTP::RequestHandler;
use HTTP::Request;

has 'handlers' => (
    metaclass => 'Collection::Array',
    is => 'rw',
    isa => 'ArrayRef[HTTP::RequestHandler]',
    auto_deref => 1,
    default => sub { [] },
    provides => {
        push => 'add_handler',
    }
);

__PACKAGE__->meta->make_immutable;

no Moose;

sub handle_request {
    my ($self, $request) = @_;

    my $handled;
    foreach my $handler ($self->handlers) {
        $handled = $handler->handle_request($request);
        if ($handled) {
            last;
        }
    }

    if (!$handled) {
        die "Request could not be handled";
    }
}

1;
