#!/usr/bin/env js

/*
function MyClass() {}
var Proto = MyClass.prototype;
Proto = { x:2, y:3 };
var obj = new MyClass();
*/

// 上と等価なコード
var Proto = { x:2, y:3 }
var obj = Object.create(Proto);
print(obj.x, obj.y);
