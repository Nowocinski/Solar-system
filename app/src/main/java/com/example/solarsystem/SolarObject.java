package com.example.solarsystem;

import java.io.Serializable;

public class SolarObject implements Serializable {
    private String name;
    private String text;
    private String image;

    private String video;
    private SolarObject[] moons;

    public SolarObject() {

    }
    public SolarObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public SolarObject[] getMoons() {
        return moons;
    }

    public void setMoons(SolarObject[] moons) {
        this.moons = moons;
    }
}
