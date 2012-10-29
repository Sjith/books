#!/usr/bin/env js

function no_swap(a, b) {
    var tmp = a;
    a = b;
    b = tmp;
}

var one = 1;
var two = 2;
no_swap(one, two);
print(one, two);
