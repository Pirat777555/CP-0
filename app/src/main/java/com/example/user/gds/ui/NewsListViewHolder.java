package com.example.user.gds.ui;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.user.gds.R;
import com.example.user.gds.model.Category;
import com.example.user.gds.model.News;
import com.example.user.gds.model.NewsList;

import java.util.ArrayList;

import static android.R.attr.category;
import static com.example.user.gds.ui.NewsActivity.news12;

/**
 * Created by user on 19.11.2016.
 */
public class NewsListViewHolder extends RecyclerView.ViewHolder {


    private News news;
    private TextView newsView;

    public NewsListViewHolder(View itemView) {

        super(itemView);

        newsView = (TextView) itemView.findViewById(R.id.newsTitle);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context = newsView.getContext();
                Intent i = new Intent(context, NewsActivity.class);
                i.putExtra(news12, news.getId());
                context.startActivity(i);
            }
        });
    }


    public void setNews(News news) {
     this.news= news;
            newsView.setText( " " + news.getTitle() +" "+news.getDate()+" "+news.getShortDesc());

    }
}



