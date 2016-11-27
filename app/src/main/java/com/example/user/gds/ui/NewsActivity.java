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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.category;
import static android.content.ContentValues.TAG;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

import static com.example.user.gds.R.id.webview;
import static com.example.user.gds.R.layout.news;

/**
 * Created by user on 19.11.2016.
 */

public class NewsActivity extends Activity   {

    public static String news12 = "news12";
    public static String INTENT_PARAM_CATEGORY_ID = "INTENT_PARAM_CATEGORY_ID";

 private TextView textnews;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);

       WebView webview = (WebView)this.findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("http://google.ru");

//Log.d(TAG,categoryId);

        String newsid = getIntent().getStringExtra("news12");
//News news = NewsList.getList(category).findNewsById(newsid);





    //    textnews.setText(news.getTitle() + " " + news.getDate() + " " + news.getShortDesc());


        }
    }


