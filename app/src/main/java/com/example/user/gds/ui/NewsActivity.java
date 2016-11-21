package com.example.user.gds.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.user.gds.R;
import com.example.user.gds.model.CategoriesList;
import com.example.user.gds.model.Category;
import com.example.user.gds.model.News;

import org.w3c.dom.Text;

import static android.R.attr.category;
import static com.example.user.gds.R.layout.news;
import static com.example.user.gds.ui.NewsListActivity.INTENT_PARAM_CATEGORY_ID;

/**
 * Created by user on 19.11.2016.
 */

public class NewsActivity extends Activity {

    public static String news12 = "news12";

private TextView textnews;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        String newstitle1 = getIntent().getStringExtra(news12);
        textnews = (TextView) findViewById(R.id.textnews);
        textnews.setText(newstitle1);

    }
}
