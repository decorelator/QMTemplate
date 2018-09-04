package com.techtify.qualimoments.model;

public class QMenuItem {
    private int drawable;
    private String name;
    private int id;

    public QMenuItem(int drawable, String name, int id) {
        this.drawable = drawable;
        this.name = name;
        this.id = id;
    }

    public int getDrawable() {
        return drawable;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
