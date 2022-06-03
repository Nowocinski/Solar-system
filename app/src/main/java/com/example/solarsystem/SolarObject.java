package com.example.solarsystem;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class SolarObject implements Serializable {
    private String name;
    private String text;
    private String image;

    private String video;
    private SolarObject[] moons;

    public SolarObject(JSONObject jsonObject) throws JSONException {
        this.name = jsonObject.getString("name");
        this.text = String.format("texts/earth.txt", this.name.toLowerCase())/*String.format("texts/%s.txt", name.toLowerCase())*/;
        this.image = String.format("images/%s.jpg", name.toLowerCase());
        this.video = jsonObject.optString("video");
        if (jsonObject.optJSONArray("moons") != null) {
            this.moons = getSolarObjectFromJSONArray(jsonObject.optJSONArray("moons"));
        }
    }

    public SolarObject[] getMoons() {
        return moons;
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

    public void setMoons(SolarObject[] moons) {
        this.moons = moons;
    }

    public static String loadStringFromAssets(Context context, String fileName) throws IOException {
        InputStream inputStream = context.getAssets().open(fileName);
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        return new String(buffer, "UTF-8");
    }

    public static SolarObject[] loadArrayFromJSON(Context context, String type) {
        try {
            String json = loadStringFromAssets(context, "solar.json");
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray(type);
            return getSolarObjectFromJSONArray(jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new SolarObject[0];
    }

    private static SolarObject[] getSolarObjectFromJSONArray(JSONArray jsonArray) throws JSONException {
        SolarObject[] solarObjects = new SolarObject[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            SolarObject solarObject = new SolarObject(jsonArray.getJSONObject(i));
            solarObjects[i] = solarObject;
        }
        return solarObjects;
    }

    public String getImagePath() {
        return String.format("file:///android_asset/%s", this.getImage());
    }
}
