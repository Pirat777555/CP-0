package com.example.user.gds.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.user.gds.R;
import com.example.user.gds.model.CategoriesList;
import com.example.user.gds.model.News;
import com.example.user.gds.model.NewsList;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.category;
import static com.example.user.gds.R.id.textnews;
import static com.example.user.gds.R.layout.news;

/**
 * Created by user on 19.11.2016.
 */

public class NewsActivity extends Activity {

    public static String news12 = "news12";

 private TextView textnews;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        String newstitle1 = getIntent().getStringExtra("news12");
      News news = NewsList.findNewsById(newstitle1);

        textnews = (TextView) findViewById(R.id.textnews);


            textnews.setText(news.getTitle() + " " + news.getDate() + " " + news.getShortDesc());


        }
    }


