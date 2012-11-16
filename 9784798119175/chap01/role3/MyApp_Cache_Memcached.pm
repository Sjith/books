package MyApp_Cache_Memcached;
use Moose;

with 'MyApp_Role_Cache';

has '+cache' => (
    isa => 'Cache::Memcached',
);

__PACKAGE__->meta->make_immutable;

no Moose;

sub get { shift->cache->get(@_) }
sub set { shift->cache->set(@_) }
sub delete { shift->cache->delete(@_) }
sub clear { shift->cache->flush_all(@_) }

1;
