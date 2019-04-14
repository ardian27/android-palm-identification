package com.ah.palmdroid.DB;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "min_max")
public class MinMax {

    @PrimaryKey
    private int id;

    String min_h,max_h,min_s,max_s,min_v,max_v;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMin_h() {
        return min_h;
    }

    public void setMin_h(String min_h) {
        this.min_h = min_h;
    }

    public String getMax_h() {
        return max_h;
    }

    public void setMax_h(String max_h) {
        this.max_h = max_h;
    }

    public String getMin_s() {
        return min_s;
    }

    public void setMin_s(String min_s) {
        this.min_s = min_s;
    }

    public String getMax_s() {
        return max_s;
    }

    public void setMax_s(String max_s) {
        this.max_s = max_s;
    }

    public String getMin_v() {
        return min_v;
    }

    public void setMin_v(String min_v) {
        this.min_v = min_v;
    }

    public String getMax_v() {
        return max_v;
    }

    public void setMax_v(String max_v) {
        this.max_v = max_v;
    }
}
