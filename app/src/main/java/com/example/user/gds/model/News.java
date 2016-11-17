package com.example.user.gds.model;

import android.os.AsyncTask;
import android.widget.TextView;

import com.example.user.gds.R;
import com.example.user.gds.utils.InputStreamUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11.11.2016.
 */

public class News {

    private  String date;
    private String id;
    private String shortDesc;
    private String title;
    private String fullDesc;

    public News(String date) {

    }



    public News(JSONObject json) throws JSONException {
        this.id=json.getString("id");
        this.date=json.getString("date");
        this.title=json.getString("title");
        this.shortDesc=json.getString("shortDescription");



    }
    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getShortDesc() { return shortDesc; }

    public void setShortDesc(String shortDesc) { this.shortDesc = shortDesc; }

    public  String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getFullDesc() {        return fullDesc;    }

    public void setFullDesc(String fullDesc) {        this.fullDesc = fullDesc;    }

    public  void updateNews() {

        new AsyncTask<Void, Void, List<News>>() {
            @Override


            protected List<News> doInBackground(Void... params) {


                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL("http://testtask.sebbia.com/v1/news/details?id=17").openConnection();
                    connection.setRequestMethod("GET");
                    String response = InputStreamUtils.toString(connection.getInputStream());
                    JSONObject json = new JSONObject(response);
                    JSONArray jsonArray = json.getJSONArray("news");


                } catch (Exception e) {
                    //TODO: обработка ошибок
                    return null;
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<News> news) {
                super.onPostExecute(news);

                if (news == null) {

                    }

                }

        }.execute();

    }
        }






