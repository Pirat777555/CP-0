package com.example.user.gds.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.user.gds.R;
import com.example.user.gds.model.Category;

import static com.example.user.gds.R.layout.news;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private Category category;

    private TextView titleView;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        titleView = (TextView) itemView.findViewById(R.id.title);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context = titleView.getContext();
                Intent i = new Intent(context, NewsListActivity.class);
                i.putExtra(NewsListActivity.INTENT_PARAM_CATEGORY_ID, category.getId());

                context.startActivity(i);



            }
        });
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
        titleView.setText(category.getName());
    }


}
