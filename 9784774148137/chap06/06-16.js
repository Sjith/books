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

function MyClass(msg) {
    this.msg = msg;
    this.show = function() { print(this.msg, 'is called'); };
}

var obj1 = new MyClass('listener1');
var obj2 = new MyClass('listener2');

emitter.register(obj1.show);
emitter.register(obj2.show);

emitter.onOpen();
