package com.example.user.gds.model;

import org.json.JSONException;
import org.json.JSONObject;

import static android.R.attr.id;

public class Category {

    private  String  id;
    private  String name;
    public static String id_news;

    public Category() {

    }

    public Category(JSONObject json) throws JSONException {
        this.id = json.getString("id");
        this.name = json.getString("name");
        id_news=id;
    }


    public    String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
