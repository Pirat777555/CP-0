package com.example.user.gds.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.user.gds.R;
import com.example.user.gds.model.CategoriesList;
import com.example.user.gds.model.Category;
import com.example.user.gds.model.News;
import com.example.user.gds.model.NewsList;

import java.util.List;

import static com.example.user.gds.R.id.recyclerView;
import static com.example.user.gds.R.id.recyclerView2;

public class NewsListActivity extends Activity implements NewsList.OnUpdateListener {

    public static final String INTENT_PARAM_CATEGORY_ID = "INTENT_PARAM_CATEGORY_ID";

    private Category category;
    private RecyclerView recyclerView;

    private NewsList newslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newslist_view);
        recyclerView = (RecyclerView) findViewById(recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String categoryId = getIntent().getStringExtra(INTENT_PARAM_CATEGORY_ID);
        category = CategoriesList.INSTANCE.findCategoryById(categoryId);
        Log.d("TAG", category.getName());
        newslist = new NewsList(category);
        newslist.getNews();
    }

@Override
    protected void onResume() {
        super.onResume();
        newslist.updateNews();
        newslist.addOnUpdateListener(this);
        refresh();
    }
    @Override
    protected void onPause() {
        super.onPause();
       newslist.removeOnUpdateListener(this);
    }
    private void refresh() {

        List <News> news = newslist.getNews();
        NewsAdapter adapter = new NewsAdapter(news);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onUpdateComplete() {
        refresh();
    }

    @Override
    public void onUpdateFailed() {

    }
}
