#!/usr/bin/env js

var emitter = {
    callbacks: [],
    register: function(fn) {
        this.callbacks.push(fn);
    },
    onOpen: function() {
        for (var i in this.callbacks) {
            this.callbacks[i]();
        }
    },
};

emitter.register(function() { print('event handler1 is called'); });
emitter.register(function() { print('event handler2 is called'); });
emitter.onOpen();
