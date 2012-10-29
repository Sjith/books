#!/usr/bin/env js

var x = 1;

function f() {
    print('x =', x);  // このxは次行で宣言しているローカル変数xなので、この時点の値はundefined
    var x = 2;
    print('x =', x);
}

f();
