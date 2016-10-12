package com.elife.classproject.adapter;

import java.io.Serializable;

/**
 * Created by tzhang on 2016/9/2.
 */
public class InfoModel implements Serializable {


    private int photo;
    private String name;
    private String describe;
    private String time;
    private int icon;

    public InfoModel() {

    }
    public InfoModel(String describe, int icon, String name, int photo, String time) {
        this.describe = describe;
        this.icon = icon;
        this.name = name;
        this.photo = photo;
        this.time = time;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
