package com.zsl.myapplication.contact.Entity;

/**
 * Created by zsl on 3/3/16.
 */
public class CityEntity {
    private String name;
    private String level;
    private int iconId;

    public CityEntity() {
    }

    public CityEntity(String name, String level, int iconId) {
        this.name = name;
        this.level = level;
        this.iconId = iconId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getIconId() {
        return this.iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
