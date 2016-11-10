package com.example.user.gds.model;

import android.os.AsyncTask;

import com.example.user.gds.utils.InputStreamUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public enum CategoriesList {
    INSTANCE;

    private List<Category> categories;

    CategoriesList() {
        categories = new ArrayList<>();
    }

    public List<Category> getCategories() {
        return categories;
    }

    public interface OnUpdateListener {
        void onUpdateComplete();
        void onUpdateFailed();
    }

    private ArrayList<OnUpdateListener> listeners = new ArrayList<>();

    public void addOnUpdateListener(OnUpdateListener listener) {
        listeners.add(listener);
    }

    public void removeOnUpdateListener(OnUpdateListener listener) {
        listeners.remove(listener);
    }

    public void updateCategories() {
        new AsyncTask<Void, Void, List<Category>>() {
            @Override
            protected List<Category> doInBackground(Void... voids) {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL("http://testtask.sebbia.com/v1/news/categories").openConnection();
                    connection.setRequestMethod("GET");
                    connection.setUseCaches(false);
                    String response = InputStreamUtils.toString(connection.getInputStream());
//                    {
//                        "code": 0,
//                            "list": [
//                        {
//                            "id": 0,
//                                "name": "Много новостей"
//                        },
//                        {
//                            "id": 1,
//                                "name": "Немного новостей"
//                        },
//                        {
//                            "id": 2,
//                                "name": "Нет новостей"
//                        }
//                        ]
//                    }
                    JSONObject json = new JSONObject(response);
                    JSONArray jsonArray = json.getJSONArray("list");
                    List<Category> result = new ArrayList<Category>();
                    for (int i = 0; i < jsonArray.length(); ++i) {
                        result.add(new Category(jsonArray.getJSONObject(i)));
                    }
                    return result;
                } catch (Exception e) {
                    //TODO: обработка ошибок
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<Category> categories) {
                super.onPostExecute(categories);

                if (categories == null) {
                    for (OnUpdateListener listener : listeners) {
                        listener.onUpdateFailed();
                    }
                } else {
                    CategoriesList.this.categories = categories;
                    for (OnUpdateListener listener : listeners) {
                        listener.onUpdateComplete();
                    }
                }
            }
        }.execute();
    }
}
