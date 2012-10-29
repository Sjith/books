#!/usr/bin/env js

function getDistance(x, y, z) {
    return Math.sqrt(x * x + y * y + z * z);
}

print(getDistance(3, 2, 2));
