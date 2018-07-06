package com.example.smile.testboolr.domain;

/**
 * Created by Smile on 2018/7/3.
 */

public class Commont {
    private String name;
    private String info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Commont{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
