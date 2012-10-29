#!/usr/bin/env js

function fn() {
    print(arguments.length);
    print(arguments[0], arguments[1], arguments[2]);
}

fn('foo', 'bar', 'baz');
