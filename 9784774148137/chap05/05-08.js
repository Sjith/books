#!/usr/bin/env js

var x = 0;
var y = 1;

function MyClass(x, y) {
    this.x = x;
    this.y = y;
}

var obj = new MyClass(3, 2);
print(obj.x, obj.y);

MyClass(5, 6);
print(x, y);
