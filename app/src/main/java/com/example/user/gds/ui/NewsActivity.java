package com.example.user.gds.ui;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.user.gds.R;
import com.example.user.gds.model.CategoriesList;
import com.example.user.gds.model.Category;
import com.example.user.gds.model.News;
import com.example.user.gds.model.NewsList;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.category;
import static android.content.ContentValues.TAG;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

import static com.example.user.gds.R.id.recyclerView;
import static com.example.user.gds.R.id.webview;
import static com.example.user.gds.R.layout.news;

/**
 * Created by user on 19.11.2016.
 */

public class NewsActivity extends Activity implements News.OnUpdateListener  {
private News news;
    private WebView  webView;
    public static String news12 = "news12";
    public static String INTENT_PARAM_CATEGORY_ID = "INTENT_PARAM_CATEGORY_ID";

 private TextView textnews;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        String categoryId = getIntent().getStringExtra("INTENT_PARAM_CATEGORY_ID");
textnews = (TextView) findViewById(R.id.newsops);
        Category category = CategoriesList.INSTANCE.findCategoryById(categoryId);
       WebView webview = (WebView)this.findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);

                String newsid = getIntent().getStringExtra("news12");
 news = NewsList.getList(category).findNewsById(newsid);



   //    webview.loadData("text/html; charset=UTF-8",null);



       textnews.setText( news.getTitle() + " " + news.getDate() + " " + news.getShortDesc()+news.getFullDesc());


        }
    @Override
    protected void onResume() {
        super.onResume();
       news.addOnUpdateListener(this);
        refresh();

    }
    @Override
    protected void onPause() {
        super.onPause();
news.removeOnUpdateListener(this);
    }
    private void refresh() {

      news.updateOneNews();

    }
    @Override
    public void onUpdateComplete() {
        refresh();
    }

    @Override
    public void onUpdateFailed() {

    }
}


