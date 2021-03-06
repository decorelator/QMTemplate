package com.techtify.qualimoments.model;

public class NotifyModel {
    private String name;
    private String info;
    private int id;

    public NotifyModel(String name, String info, int id) {
        this.name = name;
        this.info = info;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public int getId() {
        return id;
    }
}
