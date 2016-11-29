package com.example.user.gds.ui;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.gds.R;

import com.example.user.gds.model.Category;
import com.example.user.gds.model.News;
import com.example.user.gds.model.NewsList;

import java.net.CacheRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 19.11.2016.
 */
class NewsAdapter extends RecyclerView.Adapter<NewsListViewHolder> {

    private List<News> news;
    private Category category;
    public NewsAdapter(List<News> news, Category category) {
        this.category=category;
       this.news = new ArrayList<>(news);

    }

    @Override
    public NewsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.news_view, parent, false);
        return new NewsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsListViewHolder holder, int position) {
        holder.setNews(news.get(position),category);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }
}
