package ClassAccessorFastSample;
use strict;
use warnings;
use base qw(Class::Accessor::Fast);

__PACKAGE__->mk_accessors(qw(aaa bbb ccc));

package main;
use strict;
use warnings;
use Data::Dumper;

my $obj = ClassAccessorFastSample->new();
print Dumper($obj);
