package com.example.medicalapplicationsystem;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kwabenaberko.newsapilib.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.NewsHolder>{
    List<Article> articleList;
    newsAdapter(List<Article> articleList){
    this.articleList=articleList;
    }
    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.newa,parent,false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
          Article article=articleList.get(position);
          holder.title.setText(article.getTitle());
          holder.source.setText(article.getSource().getName());
        Picasso.get().load(article.getUrlToImage())
                .error(R.drawable.baseline_image_not_supported_24)
                .placeholder(R.drawable.baseline_image_not_supported_24)
                .into(holder.newsimage);


        holder.itemView.setOnClickListener(v -> {
            Intent in=new Intent(v.getContext(),NewsDetail.class);
            in.putExtra("url",article.getUrl());
            v.getContext().startActivity(in);
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public void updateData(List<Article> al) {
        articleList.clear();
        articleList.addAll(al);
    }

    class NewsHolder extends RecyclerView.ViewHolder{
           TextView title,source;
           ImageView newsimage;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            source=itemView.findViewById(R.id.sorce);
            newsimage=itemView.findViewById(R.id.aimageView);
        }
    }
}
