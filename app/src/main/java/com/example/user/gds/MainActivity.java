package com.example.user.gds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.user.gds.model.CategoriesList;
import com.example.user.gds.model.Category;
import com.example.user.gds.model.News;
import com.example.user.gds.model.NewsList;
import com.example.user.gds.ui.CategoriesListActivity;
import com.example.user.gds.ui.NewsListActivity;

import java.util.List;

import static android.R.attr.category;
import static android.R.attr.id;
import static android.R.attr.start;
import static android.R.id.list;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.os.Build.VERSION_CODES.N;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "fsdf");

        Intent i = new Intent(this, CategoriesListActivity.class);
        startActivity(i);



//        CategoriesList.INSTANCE.addOnUpdateListener(new CategoriesList.OnUpdateListener() {
//            @Override
//            public void onUpdateComplete() {
//                Log.d(MainActivity.class.getSimpleName(), "Update compelte");
//                List<Category> list = CategoriesList.INSTANCE.getCategories();
//                for (Category category : list) {
//                    Log.d(MainActivity.class.getSimpleName(), "Category: " + category.getId() + " " + category.getName());
//
//                    final NewsList newsList = new NewsList(category);
//                    newsList.addOnUpdateListener(new NewsList.OnUpdateListener() {
//                        @Override
//                        public void onUpdateComplete() {
//                            Log.d(MainActivity.class.getSimpleName(), "Update compelte");
//                            for (News news : newsList.getNews()) {
//                                Log.d(MainActivity.class.getSimpleName(), "News from category " + newsList.getCategory().getName() + ": " + news.getId() + " " + news.getTitle());
//                                news.updateNews();
//                            }
//                        }
//
//                        @Override
//                        public void onUpdateFailed() {
//                            Log.d(MainActivity.class.getSimpleName(), "Update failed");
//                        }
//                    });
//                    newsList.updateNews();
//                }
//            }
//
//            @Override
//            public void onUpdateFailed() {
//                Log.d(MainActivity.class.getSimpleName(), "Update failed");
//            }
//        });
//        CategoriesList.INSTANCE.updateCategories();
   }

}







