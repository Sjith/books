#!/usr/bin/env js

function f(arg) {
    var n = 123 + Number(arg);
    function g() {
        print('n is', n);
        print('g is called');
    }
    return g;
}

var g2 = f(2);
var g3 = f(3);

g2();
g3();
