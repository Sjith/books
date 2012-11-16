package TimestampedEchoServer;
use Moose;

extends 'EchoServer';

override 'write_response' => sub {
    my ($self, $client, $request) = @_;
    print $client scalar(localtime), " ";
    super();
};

### 引数の値を変更する場合はaroundを使用する
#around 'write_response' => sub {
#    my ($next, $self, $client, $request) = @_;
#    $request = join(' ', scalar(localtime), $request);
#    $next->($self, $client, $request);
#};

package main;

my $s = TimestampedEchoServer->new(address => "127.0.0.1", port => 9999);
#$s->bind;
$s->run;

1;
