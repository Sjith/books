#!/usr/bin/env js

function getDistance(pos) {
    pos = pos || {x:0, y:0, z:0};
    return Math.sqrt(pos.x * pos.x + pos.y * pos.y + pos.z * pos.z);
}

print(getDistance());
