package MooseSample;
use Moose;

has 'aaa' => (
    is => 'rw'
);

has 'bbb' => (
    is => 'rw'
);

has 'ccc' => (
    is => 'rw'
);

__PACKAGE__->meta->make_immutable;

no Moose;

package main;

my $obj = MooseSample->new();
$obj->aaa(123);             # write
print $obj->aaa(), "\n";    # read
