package com.example.bindservicesample;

import com.example.bindservicesample.BindActivityAIDL;

interface BindServiceAIDL {
    void registerCallback(BindActivityAIDL callback);
    void unregisterCallback(BindActivityAIDL callback);
}