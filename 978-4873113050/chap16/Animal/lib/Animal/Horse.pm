package Animal::Horse;
use strict;
use warnings;
use base qw(Animal);

=head1 NAME

Animal::Horse - Perl extension for Animal::horse

=head1 SYNOPSIS

  use Animal::Horse;
  my $horse = Animal::Horse->named('Mr. Ed');
  $horse->set_name("Mister Ed");
  $horse->set_color("grey");
  print $horse->name;   # 'Mister Ed'
  print $horse->color;  # 'grey'

=head1 DESCRIPTION

Stub documentation for Animal, created by h2xs. It looks like the
author of the extension was negligent enough to leave the stub
unedited.

=over

=item sound()
=cut
sub sound { "neight" }

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
