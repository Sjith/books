package Animal;

use 5.014002;
use strict;
use warnings;

use Carp qw(croak);

our $VERSION = '0.01';

=head1 NAME

Animal - Perl extension for Animal

=head1 SYNOPSIS

  use Animal;

=head1 DESCRIPTION

Stub documentation for Animal, created by h2xs. It looks like the
author of the extension was negligent enough to leave the stub
unedited.

=over

=item named()
=cut
sub named {
    ref(my $class = shift) and croak "class name needed";
    my $name = shift;
    my $self = { Name => $name, Color => $class->default_color };
    bless $self, $class;
}

=item default_color()
=cut
sub default_color { "brown" }

=item abstract sound()
=cut
sub sound { croak "subclass must define a sound" }

=item speak($eihter)
=cut
sub speak {
    my $either = shift;
    print $either->name, " goes ", $either->sound, "\n";
}

=item name($eihter)
=cut
sub name {
    my $either = shift;
    ref $either ? $either->{Name} : "an unnamed $either";
}

=item color($eihter)
=cut
sub color {
    my $either = shift;
    ref $either ? $either->{Color} : $either->default_color;
}

=item set_name()
=cut
sub set_name {
    ref(my $self = shift) or croak "instance variable needed";
    $self->{Name} = shift;
}

=item set_color()
=cut
sub set_color {
    ref(my $self = shift) or croak "instance variable needed";
    $self->{Color} = shift;
}

1;
__END__
=head1 SEE ALSO

Mention other useful documentation such as the documentation of
related modules or operating system documentation (such as man pages
in UNIX), or any relevant external documentation such as RFCs or
standards.

If you have a mailing list set up for your module, mention it here.

If you have a web site set up for your module, mention it here.

=head1 AUTHOR

Shoji Kai, E<lt>kai@localE<gt>

=head1 COPYRIGHT AND LICENSE

Copyright (C) 2012 by Shoji Kai

This library is free software; you can redistribute it and/or modify
it under the same terms as Perl itself, either Perl version 5.14.2 or,
at your option, any later version of Perl 5 you may have available.


=cut
