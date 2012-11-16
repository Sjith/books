package MyApp_Role_Cache;
use Moose::Role;

has 'cache' => { is => 'rw', required => 1 };

requires qw(get set delete clear);

1;
