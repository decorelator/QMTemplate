package com.techtify.qualimoments.model;

public class NetworkItem {
    private int resId;
    private String name;
    private int id;
    private String tag;

    public NetworkItem(int resId, String name, int id, String tag) {
        this.resId = resId;
        this.name = name;
        this.id = id;
        this.tag = tag;
    }

    public int getResId() {
        return resId;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }
}
