#!/usr/bin/env js

var obj = { x:3, y:4, z:5 };
for (var key in obj) {
    print('key = ', key);
    print('val = ', obj[key]);
}
