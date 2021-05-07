package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EditAddressBean implements Serializable {

    private DataDTO data;
    private Integer status;

    public EditAddressBean(DataDTO data, Integer status) {
        this.data = data;
        this.status = status;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public static class DataDTO implements Serializable {
    }
}
