package com.meishipintu.cardviewdemo.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/3.
 */

public class Data implements Serializable{
    private String title;
    private int picRes;
    private String clickShow;

    public Data(String title, int picRes, String clickShow) {
        this.title = title;
        this.picRes = picRes;
        this.clickShow = clickShow;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPicRes() {
        return picRes;
    }

    public void setPicRes(int picRes) {
        this.picRes = picRes;
    }

    public String getClickShow() {
        return clickShow;
    }

    public void setClickShow(String clickShow) {
        this.clickShow = clickShow;
    }
}
