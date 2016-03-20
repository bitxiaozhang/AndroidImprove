package com.zsl.myapplication.contact.Entity;

import java.util.List;

/**
 * Created by zsl on 3/3/16.
 */
public class ProviceEntity {
    private List<CityEntity> cityList;
    private String name;

    public ProviceEntity() {
    }

    public ProviceEntity(List<CityEntity> cityList, String name) {
        this.cityList = cityList;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityEntity> getCityList() {
        return this.cityList;
    }

    public void setCityList(List<CityEntity> cityList) {
        this.cityList = cityList;
    }
}
