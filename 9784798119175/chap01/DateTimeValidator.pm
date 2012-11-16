package DateTimeValidator;
use Moose;
use Moose::Util::TypeConstraints;
use DateTime;
use DateTime::Format::Strptime;

# DateTimeを特定のパターンから作成するパーサを定義
my $parser = DateTime::Format::Strptime->new(
    pattern => '%Y-%m-%d %H:%M:%S',
    time_zone => 'local',
);

# DateTimeというクラスが存在することをMooseに伝える
class_type 'DateTime';

coerce 'DateTime'
    => from 'Str'
    => via { $parser->parse_datetime($_) }
;

coerce 'DateTime'
    => from 'HashRef'
    => via { DateTime->new(%$_) }
;

has 'datetime' => (
    is => 'rw',
    isa => 'DateTime',
    coerce => 1,
);

__PACKAGE__->meta->make_immutable;

no Moose;
no Moose::Util::TypeConstraints;

package main;

my $obj = DateTimeValidator->new;

$obj->datetime('2008-12-15 09:43:00');
$obj->datetime({
    year => 2008,
    month => 12,
    day => 15,
    hour => 9,
    minute => 43,
});

1;
