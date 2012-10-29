#!/usr/bin/env js

function swap(a, b) {
    return [b, a];
}

var one = 1;
var two = 2;
[one, two] = swap(one, two);
print(one, two);
