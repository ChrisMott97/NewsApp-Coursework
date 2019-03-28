package com.chrism.news;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<Article> articles;
    private Context context;

    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        CardView cv;
        TextView title;
        TextView desc;
        ImageView img;
        MaterialButton explore;

        public NewsViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            img = itemView.findViewById(R.id.img);
            explore = itemView.findViewById(R.id.explore);
        }
    }

    public NewsAdapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    public NewsAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // create new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        NewsViewHolder vh = new NewsViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position){
        holder.title.setText(articles.get(position).getTitle());
        holder.desc.setText(articles.get(position).getDescription());
        holder.explore.setOnClickListener((item) -> {
//            Log.d("MyTag", holder.title.getText().toString() + " clicked!");
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(articles.get(position).getUrl()));
            context.startActivity(browserIntent);
        });
        Glide.with(holder.itemView).load(articles.get(position).getUrlToImage()).into(holder.img);
    }

    @Override
    public int getItemCount(){
        return articles.size();
    }
}
