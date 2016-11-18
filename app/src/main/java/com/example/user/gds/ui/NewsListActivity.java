package com.example.user.gds.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.user.gds.model.CategoriesList;
import com.example.user.gds.model.Category;

public class NewsListActivity extends Activity {

    public static final String INTENT_PARAM_CATEGORY_ID = "INTENT_PARAM_CATEGORY_ID";

    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String categoryId = getIntent().getStringExtra(INTENT_PARAM_CATEGORY_ID);
        category = CategoriesList.INSTANCE.findCategoryById(categoryId);
        Log.d("TAG", category.getName());
    }
}
