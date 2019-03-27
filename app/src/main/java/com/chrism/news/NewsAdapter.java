package com.chrism.news;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<Article> articles;

    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        CardView cv;
        TextView title;
        TextView desc;
        ImageView img;
        public NewsViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            img = itemView.findViewById(R.id.img);
        }
    }

    public NewsAdapter(List<Article> articles) {
        this.articles = articles;
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
        Glide.with(holder.itemView).load(articles.get(position).getUrlToImage()).into(holder.img);
    }

    @Override
    public int getItemCount(){
        return articles.size();
    }
}
