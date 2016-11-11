package com.example.user.gds.model;

/**
 * Created by user on 11.11.2016.
 */
import android.os.AsyncTask;

import com.example.user.gds.utils.InputStreamUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public enum NewsList {
    INSTANCE;

    private List<News> newses;

    NewsList() {
        newses = new ArrayList<>();
    }

    public List<News> getNewses() {
        return newses;
    }

    public interface OnUpdateListener {

        void onUpdateComplete();

        void onUpdateFailed();

    }

    private ArrayList<NewsList.OnUpdateListener> listeners = new ArrayList<>();

    public void addOnUpdateListener(NewsList.OnUpdateListener listener) {
        listeners.add(listener);
    }

    public void removeOnUpdateListener(NewsList.OnUpdateListener listener) {
        listeners.remove(listener);
    }

    public void updateNews() {
        new AsyncTask<Void, Void, List<News>>() {
            @Override
            protected List<News> doInBackground(Void... voids) {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL("http://testtask.sebbia.com/v1/news/categories/0/news").openConnection();
                    connection.setRequestMethod("GET");
                    connection.setUseCaches(false);
                    String response = InputStreamUtils.toString(connection.getInputStream());
                    JSONObject json = new JSONObject(response);
                    JSONArray jsonArray = json.getJSONArray("list");
                    List<News> result = new ArrayList<News>();
                    for (int i = 0; i < jsonArray.length(); ++i) {
                        result.add(new News(jsonArray.getJSONObject(i)));
                    }
                    return result;
                } catch (Exception e) {
                    //TODO: обработка ошибок
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<News> newses) {
                super.onPostExecute(newses);

                if (newses == null) {
                    for (OnUpdateListener listener : listeners) {
                        listener.onUpdateFailed();
                    }
                } else {
                    NewsList.this.newses = newses;
                    for (OnUpdateListener listener : listeners) {
                        listener.onUpdateComplete();
                    }
                }
            }
        }.execute();
    }
    }




