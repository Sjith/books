package com.example.servicemondai2;

import com.example.servicemondai2.ActivityAIDL;

interface ServiceAIDL {
    void startService(ActivityAIDL callback);
    void stopService();
    void resetService(ActivityAIDL callback);
}