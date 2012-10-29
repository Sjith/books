#!/usr/bin/env js

function myclass(x, y) {
    return { show: function() { print(x, y); } };
}

var obj = myclass(3, 2);
obj.show();
