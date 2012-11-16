package FontFactory;
use Moose;
use MooseX::AttributeHelpers;

has 'fonts' => (
    metaclass => 'Collection::Hash',
    is => 'rw',
    isa => 'HashRef[Object]',
    default => sub { {} },
    provides => {
        get => 'get_font',
        set => 'set_font',
    },
);

__PACKAGE__->meta->make_immutable;

no Moose;

sub create {
    my ($self, $name, $size) = @_;

    my $key = join('.', $name, $size);
    my $font = $self->get_font($key);
    if (!$font) {
        $font = Font->new(name => $name, size => $size);
        $self->set_font($key, $font);
    }

    return $font;
}

1;
