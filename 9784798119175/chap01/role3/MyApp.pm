package MyApp;
use Moose;

has 'cache' => (
    is => 'rw',
    does => 'MyApp::Role::Cache',
    handles => {
        cache_get => 'get',
        cache_set => 'set',
        cache_delete => 'delete',
        cache_clear => 'clear',
    },
);

__PACKAGE__->meta->make_immutable;

no Moose;

### handlesが行っているのは以下のコードと同等
#sub cache_get { shift->cache->get(@_) }
#sub cache_set { shift->cache->set(@_) }
#sub cache_delete { shift->cache->delete(@_) }
#sub cache_clear { shift->cache->clear(@_) }

1;
