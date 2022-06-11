package com.zayanpati.earthquakeinfo;

public class Info {
    String location;
    double magnitude;
    Long timeInMilliSeconds;
    String url;

    public String getLocation() {
        return location;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public Long getTimeInMilliSeconds() {
        return timeInMilliSeconds;
    }

    public String getUrl() {
        return url;
    }

    public Info(String location, double magnitude, Long timeInMilliSeconds, String url) {
        this.location = location;
        this.magnitude = magnitude;
        this.timeInMilliSeconds = timeInMilliSeconds;
        this.url = url;
    }
}
