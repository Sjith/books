package MyApp_Cache_Cache;
use Moose;

with 'MyApp_Role_Cache';

has '+cache' => (
    isa => 'Cache::Cache',
);

__PACKAGE__->meta->make_immutable;

no Moose;

sub get { shift->cache->get(@_) }
sub set { shift->cache->set(@_) }
sub delete { shift->cache->delete(@_) }
sub clear { shift->cache->clear(@_) }

1;
