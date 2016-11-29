package com.example.user.gds.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
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

import static android.R.attr.category;
import static android.content.ContentValues.TAG;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.os.Build.VERSION_CODES.N;
import static android.webkit.ConsoleMessage.MessageLevel.LOG;
import static com.example.user.gds.R.layout.news;
import static com.example.user.gds.R.layout.news_view;

/**
 * Created by user on 11.11.2016.
 */

public class News {

    private  ArrayList<News.OnUpdateListener> listeners = new ArrayList<>();

    private String date;
    private String id;
    private String shortDesc;
    private String title;
    private String fullDesc;


    public News(JSONObject jsonObject) throws JSONException {

        parseJson(jsonObject);


    }


    public void parseJson(JSONObject jsonObject) throws JSONException {

        this.id = jsonObject.getString("id");
        this.date = jsonObject.getString("date");
        this.title = jsonObject.getString("title");
        this.shortDesc = jsonObject.getString("shortDescription");
        if (jsonObject.has("fullDescription")){
            this.fullDesc=jsonObject.getString("fullDescription");}

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullDesc() {        return fullDesc;    }

    public void setFullDesc(String fullDesc) {        this.fullDesc = fullDesc;    }

    public interface OnUpdateListener {

        void onUpdateComplete();

        void onUpdateFailed();


    }
    public  void addOnUpdateListener(News.OnUpdateListener listener) {
        listeners.add(listener);
    }

    public void removeOnUpdateListener(News.OnUpdateListener listener) {
        listeners.remove(listener);
    }
    public void updateOneNews() {
        new AsyncTask<Void, Void,Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                try {

                    HttpURLConnection connection = (HttpURLConnection) new URL("http://testtask.sebbia.com/v1/news/details?id="+id).openConnection();
                    connection.setRequestMethod("GET");
                    connection.setUseCaches(false);
                    String response = InputStreamUtils.toString(connection.getInputStream());
                    JSONObject json = new JSONObject(response);
                    json = json.getJSONObject("news");
                    JSONObject jsonObject = json.getJSONObject("news");

                    new News(jsonObject);
                                        return true;
                } catch (Exception e) {
                    //TODO: обработка ошибок
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean success) {
                super.onPostExecute(success);

                if (success == false) {
                    for (News.OnUpdateListener listener : listeners) {
                        listener.onUpdateFailed();
                    }

                } else {

                    for (News.OnUpdateListener listener : listeners) {
                        listener.onUpdateComplete();

                    }
                }
            }
        }.execute();
    }



}





