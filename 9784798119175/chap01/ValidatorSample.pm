package ValidatorSample;
use Moose;
use Moose::Util::TypeConstraints;

subtype 'ValidatorSample::MultipleOfThree'
    => as 'Int'
    => where { $_ % 3 == 0 }
    => message { "This number ($_) is not divisible by three!" }
;

coerce 'ValidatorSample::MultipleOfThree'
    => from 'Int',
    => via { $_ - $_ % 3 }
;

has 'number' => (
    is => 'rw',
    isa => 'Maybe[Int]',
);

has 'number_list' => (
    is => 'rw',
    isa => 'ArrayRef[Int]',
);

has 'number_hash' => (
    is => 'rw',
    isa => 'HashRef[Int]',
);

has 'number3' => (
    is => 'rw',
    isa => 'ValidatorSample::MultipleOfThree',
);

has 'number3_coerce' => (
    is => 'rw',
    isa => 'ValidatorSample::MultipleOfThree',
    coerce => 1,
);

__PACKAGE__->meta->make_immutable;

no Moose;
no Moose::Util::TypeConstraints;

package main;

my $v = ValidatorSample->new;

$v->number(100);     # ok
#$v->number("hoge"); # ng

$v->number_list([1,2,3,4,5]);           # ok
#$v->number_list(['a','b','c','d','e']); # ng

$v->number_hash({a=>1, b=>2, c=>3});    # ok
#$v->number_hash({a=>"foo", b=>"bar"});  # ng

$v->number3(3);    # ok
$v->number3(6);    # ok
#$v->number3(2);    # ng

$v->number3_coerce(8);    # ok (6)
print $v->number3_coerce, "\n";
