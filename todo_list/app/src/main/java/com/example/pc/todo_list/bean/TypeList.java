package com.example.pc.todo_list.bean;

/**
 * Created by HCD-Fresher057 on 1/11/2017.
 */

public class TypeList {
    int id;
    String kieu_danh_sach;

    public TypeList() {
    }

    public TypeList(int id, String kieu_danh_sach) {
        this.id = id;
        this.kieu_danh_sach = kieu_danh_sach;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKieu_danh_sach() {
        return kieu_danh_sach;
    }

    public void setKieu_danh_sach(String kieu_danh_sach) {
        this.kieu_danh_sach = kieu_danh_sach;
    }

    @Override
    public String toString() {
        return id +". "+kieu_danh_sach;
    }
}
