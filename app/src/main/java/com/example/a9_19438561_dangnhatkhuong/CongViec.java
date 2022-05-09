package com.example.a9_19438561_dangnhatkhuong;

import java.io.Serializable;

public class CongViec implements Serializable {

    private String id, tenCongViec, ghiChu;

    public CongViec(String id, String tenCongViec, String ghiChu) {
        this.id = id;
        this.tenCongViec = tenCongViec;
        this.ghiChu = ghiChu;
    }

    public CongViec(String tenCongViec, String ghiChu) {
        this.tenCongViec = tenCongViec;
        this.ghiChu = ghiChu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenCongViec() {
        return tenCongViec;
    }

    public void setTenCongViec(String tenCongViec) {
        this.tenCongViec = tenCongViec;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }


}
