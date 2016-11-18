package com.example.user.gds.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.gds.R;
import com.example.user.gds.model.CategoriesList;
import com.example.user.gds.model.Category;

import java.util.List;

public class CategoriesListActivity extends Activity implements CategoriesList.OnUpdateListener {

    private RecyclerView recyclerView;
    private CategoriesList categoriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_list);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        categoriesList = CategoriesList.INSTANCE;
    }

    @Override
    protected void onResume() {
        super.onResume();
        categoriesList.updateCategories();
        categoriesList.addOnUpdateListener(this);
        refresh();
    }

    @Override
    protected void onPause() {
        super.onPause();
        categoriesList.removeOnUpdateListener(this);
    }

    private void refresh() {
        List<Category> categories = categoriesList.getCategories();
        CategoriesAdapter adapter = new CategoriesAdapter(categories);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onUpdateComplete() {
        refresh();
    }

    @Override
    public void onUpdateFailed() {
        //TODO
    }
}
