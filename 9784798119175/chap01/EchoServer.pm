package EchoServer;
use Moose;

has 'address' => (
    is       => 'rw',
    isa      => 'Str',
    required => 1,
);

has 'port' => (
    is       => 'rw',
    isa      => 'Int',
    required => 1,
    default  => 9999,
);

has 'server_socket' => (
    is  => 'rw',
    isa => 'IO::Socket',

    ### オブジェクトの初期化には関数リファレンスを指定する
    ### 但し、defaultの処理順序は保証されないので以下のコードだと
    ### addressやportがundefinedの可能性がある
    ### 他のプロパティに依存する場合、lazy, builderを使用する
    #default => sub {
    #    my $self = shift;
    #    IO::Socket::INET->new(
    #        Listen => 5,
    #        LocalAddr => $self->address,
    #        LocalPort => $self->port,
    #        Proto => 'tcp',
    #    );
    #},

    #lazy => 1,
    #builder => 'build_server_socket',

    ### 上記と同様。_build_<propertyName>が自動で呼び出される
    lazy_build => 1,
);

__PACKAGE__->meta->make_immutable;

no Moose;

use IO::Socket::INET;

### new()呼び出し時の処理の流れ
### 1.   my $meta = EchoServer->meta;               # Class::MOP::Classが格納されている
### 2.   my $args = EchoServer->BUILDARGS(@_);
### 3.   my $ojbect = $meta->new_object(%$args);
### 4.   $object->BUILD($args);
### 5.   return $object;

### newを使って初期化時にbindまでやっちゃう方法
#sub new {
#    my $class = shift;
#    my $self = $class->SUPER::new(@_);
#
#    $self->bind();
#    return $self;
#}

sub BUILDARGS {
    my ($class, %args) = @_;

    if (!$args{server_socket}) {
        $args{server_socket} = IO::Socket::INET->new(
            Listen => 5,
            LocalAddr => $args{address},
            LocalPort => $args{port},
            Proto => 'tcp',
        );
        if ($@) {
            die "ソケットを作成できませんでした:$@";
        }
    }
    return {%args};     # 最後にハッシュリファレンスを返す
}

### Mooseでは初期化にはBUILDを使う (コンストラクタは終わっている状態で呼ばれる)
sub BUILD {
    my $self = shift;
    #$self->bind;
    return $self;
}

#sub build_server_socket {
#    my $self = shift;
#    IO::Socket::INET->new(
#        Listen => 5,
#        LocalAddr => $self->address,
#        LocalPort => $self->port,
#        Proto => 'tcp',
#    );
#}

sub _build_server_socket {
    my $self = shift;
    IO::Socket::INET->new(
        Listen => 5,
        LocalAddr => $self->address,
        LocalPort => $self->port,
        Proto => 'tcp',
    );
}

sub bind {
    my $self = shift;

    my $socket = IO::Socket::INET->new(
        Listen => 5,
        LocalAddr => $self->address,
        LocalPort => $self->port,
        Proto => 'tcp',
    );
    if (!$socket) {
        die "ソケットを作成できませんでした:$@";
    }

    $self->server_socket($socket);
}

sub run {
    my $self = shift;

    my $socket = $self->server_socket;
    while (my $client = $socket->accept) {
        $self->process_request($client);
    }
}

sub process_request {
    my ($self, $client) = @_;

    while (!$client->eof) {
        my $req = $self->read_request($client);
        $self->write_response($client, $req);
    }
}

# Echoサーバなので1行読むだけ
sub read_request {
    my ($self, $client) = @_;
    my $line = <$client>;
    return $line;
}

# Echoサーバなので1行よんだら書き込むだけ
sub write_response {
    my ($self, $client, $request) = @_;
    print $client $request;
}

#package main;
#
#my $s = EchoServer->new(address => "127.0.0.1", port => 9999);
#$s->bind;
#$s->run;

1;
