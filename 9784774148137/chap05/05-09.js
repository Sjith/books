#!/usr/bin/env js

function MyClass(x, y) {
    this.x = x;
    this.y = y;
    this.show = function() {
        print(this.x, this.y);
    };
}

var obj = new MyClass(3, 2);
obj.show();
