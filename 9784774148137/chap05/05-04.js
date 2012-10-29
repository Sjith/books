#!/usr/bin/env js

function getDistance(pos) {
    return Math.sqrt(pos.x * pos.x + pos.y * pos.y + pos.z * pos.z);
}

print(getDistance({x:3, y:2, z:2}));
