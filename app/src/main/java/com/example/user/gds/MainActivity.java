package com.example.user.gds;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.example.user.gds.model.CategoriesList;
import com.example.user.gds.model.Category;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CategoriesList.INSTANCE.addOnUpdateListener(new CategoriesList.OnUpdateListener() {
            @Override
            public void onUpdateComplete() {
                Log.d(MainActivity.class.getSimpleName(), "Update compelte");
                List<Category> list = CategoriesList.INSTANCE.getCategories();
                for (Category category : list) {
                    Log.d(MainActivity.class.getSimpleName(), "Category: " + category.getId() + " " + category.getName());
                }
            }

            @Override
            public void onUpdateFailed() {
                Log.d(MainActivity.class.getSimpleName(), "Update failed");
            }
        });
        CategoriesList.INSTANCE.updateCategories();
    }
}
