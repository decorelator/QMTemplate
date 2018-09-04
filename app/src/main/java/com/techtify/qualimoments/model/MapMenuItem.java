package com.techtify.qualimoments.model;

public class MapMenuItem {
    private int resId;
    private String name;
    private int id;

    public MapMenuItem(int resId, String name, int id) {
        this.resId = resId;
        this.name = name;
        this.id = id;
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
}
