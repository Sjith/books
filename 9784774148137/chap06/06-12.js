#!/usr/bin/env js

var obj = (function() {
    var position = { x:2, y:3 };

    function sum_internal(a, b) {
        return Number(a) + Number(b);
    }

    return {
        sum: function(a, b) { return sum_internal(a, b); },
        x: position.x
    };
})();

print(obj.sum(3, 4));
print(obj.x);
