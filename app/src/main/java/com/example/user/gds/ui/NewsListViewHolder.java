package com.example.user.gds.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.user.gds.R;
import com.example.user.gds.model.Category;
import com.example.user.gds.model.News;
import com.example.user.gds.model.NewsList;

import static android.R.attr.category;

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
                i.putExtra(NewsActivity.news12, news.getId() + " " + news.getTitle());
                context.startActivity(i);
            }
        });
    }
    public News getNews()
    {
        return  news;
    }

    public void setNews(News news) {
        this.news = news;
        newsView.setText(news.getId()+" "+news.getTitle());
    }
}



