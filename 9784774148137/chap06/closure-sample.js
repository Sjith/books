#!/usr/bin/env js

function f() {
    var cnt = 0;
    return function() { return ++cnt; }
}

var fn = f();
print(fn());
print(fn());
print(fn());
