package MooseXAttributeHelpersSample;
use Moose;
use MooseX::AttributeHelpers;

has 'list' => (
    metaclass => 'Collection::Array',
    is => 'rw',
    isa => 'ArrayRef',
    default => sub { [] },
    provides => {
        #push => 'push_list',
        #pop => 'pop_list',
        push => 'push',
        pop => 'pop',
    },
);

__PACKAGE__->meta->make_immutable;

no Moose;

package main;

my $obj = MooseXAttributeHelpersSample->new;
#$obj->push_list('abc');
#print $obj->pop_list, "\n";
$obj->push('abc');
print $obj->pop, "\n";

1;
