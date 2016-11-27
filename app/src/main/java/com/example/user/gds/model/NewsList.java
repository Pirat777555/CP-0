package com.example.user.gds.model;

/**
 * Created by user on 11.11.2016.
 */
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.example.user.gds.MainActivity;
import com.example.user.gds.R;
import com.example.user.gds.ui.NewsListActivity;
import com.example.user.gds.utils.InputStreamUtils;
import com.example.user.gds.model.Category;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static android.R.attr.category;
import static android.R.attr.id;
import static android.R.attr.widgetCategory;
import static android.R.id.list;
import static android.os.Build.VERSION_CODES.M;
import static android.os.Build.VERSION_CODES.N;
import static com.example.user.gds.model.CategoriesList.INSTANCE;
import static com.example.user.gds.ui.NewsActivity.news12;

public class NewsList {


    private final Category category;
    private List<News> news;
    private static Map<String, NewsList> listsByCategoryId = new HashMap<>();

   private NewsList(Category category) {
        news = new ArrayList<>();
        this.category = category;


    }
    public static NewsList getList(Category category) {

        NewsList existingList = listsByCategoryId.get(category.getId());
       if (existingList != null) {
            return existingList;
        }
        NewsList newsList = new NewsList(category);
       listsByCategoryId.put(category.getId(), newsList);
        return newsList;
    }
    public Category getCategory() {
        return category;
    }

    public  List<News> getNews() {

        return news;
    }


    public interface OnUpdateListener {

        void onUpdateComplete();

        void onUpdateFailed();

    }

    private  ArrayList<NewsList.OnUpdateListener> listeners = new ArrayList<>();

    public  void addOnUpdateListener(NewsList.OnUpdateListener listener) {
        listeners.add(listener);
    }

    public void removeOnUpdateListener(NewsList.OnUpdateListener listener) {
        listeners.remove(listener);
    }
    public News findNewsById(String newsid) {
        for (News news1 : news ) {

            if (news1.getId().equalsIgnoreCase(newsid)) {
                return news1;
            }
        }
        throw new RuntimeException("News not found " + newsid);
    }

    public void updateNews() {
        new AsyncTask<Void, Void, List<News>>() {
            @Override
            protected List<News> doInBackground(Void... voids) {
                try {

                    HttpURLConnection connection = (HttpURLConnection) new URL("http://testtask.sebbia.com/v1/news/categories/" + category.getId() + "/news").openConnection();
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
                    NewsList.this.news = news;
                    for (OnUpdateListener listener : listeners) {
                        listener.onUpdateComplete();
                    }
                }
            }
        }.execute();
    }
    public void updateOneNews() {
        new AsyncTask<Void, Void, List<News>>() {
            @Override
            protected List<News> doInBackground(Void... voids) {
                try {

                    HttpURLConnection connection = (HttpURLConnection) new URL("http://testtask.sebbia.com/v1/news/details?id=18").openConnection();
                    connection.setRequestMethod("GET");
                    connection.setUseCaches(false);
                    String response = InputStreamUtils.toString(connection.getInputStream());
                    JSONObject json2 = new JSONObject(response);

                    JSONArray jsonArray = json2.getJSONArray("list");
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

                } else {

                }
            }
        }.execute();
    }



}





