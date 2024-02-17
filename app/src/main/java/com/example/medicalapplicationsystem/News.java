package com.example.medicalapplicationsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

public class News extends AppCompatActivity {
     RecyclerView nRecyclerView;
     List<Article> articleList=new ArrayList<>();
     LinearProgressIndicator progressIndicator;
     newsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        nRecyclerView=findViewById(R.id.news_recycle);
        progressIndicator=findViewById(R.id.pp);
        setnRecyclerView();
        getnews();
    }
   void setnRecyclerView(){
       nRecyclerView.setLayoutManager(new LinearLayoutManager(this));
       adapter=new newsAdapter(articleList);
       nRecyclerView.setAdapter(adapter);
   }
   void changeInProgress(boolean show){
        if (show)
            progressIndicator.setVisibility(View.VISIBLE);
        else
            progressIndicator.setVisibility(View.INVISIBLE);
   }
    private void getnews() {
        changeInProgress(true);
        NewsApiClient newsApiClient=new NewsApiClient("d4c35a32c4ee43928a690bf9665a6c6e");
        //country=in&category=health&apiKey=d4c35a32c4ee43928a690bf9665a6c6e
        newsApiClient.getTopHeadlines(new TopHeadlinesRequest.Builder()
                .category("health").
                language("en").
                country("in").build(), new NewsApiClient.ArticlesResponseCallback() {
            @Override
            public void onSuccess(ArticleResponse response) {
               // Log.e("getTitle", article.getTitle());
                runOnUiThread(() -> {
                    changeInProgress(false);
                    articleList=response.getArticles();
                    adapter.updateData(articleList);
                    adapter.notifyDataSetChanged();
                });
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

    public void goback(View view) {
        onBackPressed();
    }
}