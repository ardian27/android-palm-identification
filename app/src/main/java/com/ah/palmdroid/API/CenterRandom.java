package com.ah.palmdroid.API;

public class CenterRandom {

    public String h,s,v, Target;

    public CenterRandom(String h, String s, String v, String target) {
        this.h = h;
        this.s = s;
        this.v = v;
        this.Target = target;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getTarget() {
        return Target;
    }

    public void setTarget(String target) {
        this.Target = target;
    }
}
