#!/usr/bin/env js

// クロージャの落とし穴
function f(arg) {
    var n = 123 + Number(arg);
    function g() { print('n is', n); console.log('g is called'); }
    n++;
    function gg() { print('n is', n); console.log('gg is called'); }
    return [g, gg];
}

var g_and_gg = f(1);
g_and_gg[0]();
g_and_gg[1]();
