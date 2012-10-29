#!/usr/bin/env js

function createObject() {
    return {
        x: 3,
        y: 2,
        z: 2,
        getDistance: function() {
            return Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
        },
    };
}

var obj = createObject();
print(obj.getDistance());
