package com.goldensky.entity.base.net;


import java.util.HashMap;

public class NetParams extends HashMap<String, String> {
    public static NetParams create() {
        return new NetParams();
    }

    public NetParams add(String key, String value) {
        put(key, value);
        return this;
    }
}
