package com.ah.palmdroid.API;

public class ThresholdSpread {

    public String threshold,spread;

    public ThresholdSpread(String threshold, String spread) {
        this.threshold = threshold;
        this.spread = spread;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    public String getSpread() {
        return spread;
    }

    public void setSpread(String spread) {
        this.spread = spread;
    }
}
