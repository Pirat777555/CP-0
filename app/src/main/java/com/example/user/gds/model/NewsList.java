package com.example.user.gds.model;

/**
 * Created by user on 11.11.2016.
 */
import android.os.AsyncTask;
import android.util.Log;

import com.example.user.gds.MainActivity;
import com.example.user.gds.utils.InputStreamUtils;
import com.example.user.gds.model.Category;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.category;
import static android.R.attr.id;
import static com.example.user.gds.model.CategoriesList.INSTANCE;

public class NewsList {

    List<Category> list = CategoriesList.INSTANCE.getCategories();

   private static String id="0";



    private static List<News> news;
    NewsList() {
        news = new ArrayList<>();
    }

    public static List<News> getNews() {
        return news;
    }

    public interface OnUpdateListener {

        void onUpdateComplete();

        void onUpdateFailed();

    }

    private static ArrayList<NewsList.OnUpdateListener> listeners = new ArrayList<>();

    public static void addOnUpdateListener(NewsList.OnUpdateListener listener) {
        listeners.add(listener);
    }

    public void removeOnUpdateListener(NewsList.OnUpdateListener listener) {
        listeners.remove(listener);
    }

    public static void updateNews() {
        new AsyncTask<Void, Void, List<News>>() {
            @Override
            protected List<News> doInBackground(Void... voids) {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL("http://testtask.sebbia.com/v1/news/categories/"+id+"/news").openConnection();
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
            protected void onPostExecute(List<News> news) {
                super.onPostExecute(news);

                if (news == null) {
                    for (OnUpdateListener listener : listeners) {
                        listener.onUpdateFailed();
                    }
                } else {
                    NewsList.news = news;
                    for (OnUpdateListener listener : listeners) {
                        listener.onUpdateComplete();
                    }
                }
            }
        }.execute();
    }
    }




