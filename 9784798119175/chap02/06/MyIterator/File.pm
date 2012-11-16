package MyIterator::File;
use Moose;
use MooseX::Types::Path::Class;

with 'MyIterator';

has 'file' => (
    is => 'rw',
    isa => 'Path::Class::File',
    required => 1,
    coerce => 1,
);

has 'fh' => (
    is => 'rw',
    isa => 'IO::File',
    lazy_build => 1,
);

__PACKAGE__->meta->make_immutable;

no Moose;

sub _build_fh {
    my $self = shift;
    return $self->file->openr();
}

sub next {
    my $self = shift;

    my $fh = $self->fh;
    my $line = <$fh>;

    return $line ? $line : ();
}

1;
