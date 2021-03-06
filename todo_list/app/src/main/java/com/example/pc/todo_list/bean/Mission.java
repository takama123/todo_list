package com.example.pc.todo_list.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HCD-Fresher057 on 1/11/2017.
 */

public class Mission implements Parcelable{
    int _id;
    String m_ten_nhiem_vu;
    String m_ngay_het_han;
    String m_gio_het_han;
    int m_status;
    int m_id_danhsach;


    public Mission() {
        super();
    }

    public Mission(int _id, String m_gio_het_han, int m_id_danhsach, String m_ngay_het_han, int m_status, String m_ten_nhiem_vu) {
        this._id = _id;
        this.m_gio_het_han = m_gio_het_han;
        this.m_id_danhsach = m_id_danhsach;
        this.m_ngay_het_han = m_ngay_het_han;
        this.m_status = m_status;
        this.m_ten_nhiem_vu = m_ten_nhiem_vu;
    }

    protected Mission(Parcel in) {
        _id = in.readInt();
        m_ten_nhiem_vu = in.readString();
        m_ngay_het_han = in.readString();
        m_gio_het_han = in.readString();
        m_status = in.readInt();
        m_id_danhsach = in.readInt();
    }

    public static final Creator<Mission> CREATOR = new Creator<Mission>() {
        @Override
        public Mission createFromParcel(Parcel in) {
            return new Mission(in);
        }

        @Override
        public Mission[] newArray(int size) {
            return new Mission[size];
        }
    };

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getM_gio_het_han() {
        return m_gio_het_han;
    }

    public void setM_gio_het_han(String m_gio_het_han) {
        this.m_gio_het_han = m_gio_het_han;
    }

    public int getM_id_danhsach() {
        return m_id_danhsach;
    }

    public void setM_id_danhsach(int m_id_danhsach) {
        this.m_id_danhsach = m_id_danhsach;
    }

    public String getM_ngay_het_han() {
        return m_ngay_het_han;
    }

    public void setM_ngay_het_han(String m_ngay_het_han) {
        this.m_ngay_het_han = m_ngay_het_han;
    }

    public int getM_status() {
        return m_status;
    }

    public void setM_status(int m_status) {
        this.m_status = m_status;
    }

    public String getM_ten_nhiem_vu() {
        return m_ten_nhiem_vu;
    }

    public void setM_ten_nhiem_vu(String m_ten_nhiem_vu) {
        this.m_ten_nhiem_vu = m_ten_nhiem_vu;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(_id);
        parcel.writeString(m_ten_nhiem_vu);
        parcel.writeString(m_ngay_het_han);
        parcel.writeString(m_gio_het_han);
        parcel.writeInt(m_status);
        parcel.writeInt(m_id_danhsach);
    }
}
