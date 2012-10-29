#!/usr/bin/env js

function counter_class(init) {
    var cnt = init || 0;

    return {
        show: function() { print(cnt); },
        up: function() { cnt++; return this; },
        down: function() { cnt--; return this; }
    };
}

var counter1 = counter_class();
counter1.show();
counter1.up();
counter1.show();

var counter2 = counter_class(10);
counter2.up().up().up().show();
