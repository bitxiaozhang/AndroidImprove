package com.zsl.myapplication.find;

/**
 * Created by zsl on 3/15/16.
 */
public class ProgramCourse {
    private String name;
    private int icon;

    public ProgramCourse(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public ProgramCourse() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return this.icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
