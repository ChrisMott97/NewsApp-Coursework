package com.chrism.news;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HTTPHandler extends AsyncTask<String ,String, NewsResponse> {

    private static final String api_key = "c97d71b1e50a454b8987b61269b52f30";
    WeakReference<View> weakReference;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private AppDatabase appDatabase;

    private List<Article> articles;

    public HTTPHandler(View view){
        weakReference = new WeakReference<>(view);
    }

    @Override
    protected NewsResponse doInBackground(String... params) {
        try{
            String json = fetch(params);
            Moshi moshi = new Moshi.Builder().build();
            JsonAdapter<NewsResponse> jsonAdapter = moshi.adapter(NewsResponse.class);
            NewsResponse newsResponse = jsonAdapter.fromJson(json);

            if(newsResponse != null){
                appDatabase.articleDao().deleteAll();
                for (Article article :
                        newsResponse.getArticles()) {
                    appDatabase.articleDao().insert(article);
                }
                Log.d("MyTag", appDatabase.articleDao().getAll().toString());
                articles = newsResponse.getArticles();
            }else{
                articles = appDatabase.articleDao().getAll();
            }


            return newsResponse;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }


    }

    private String fetch(String... params) throws IOException{
        OkHttpClient client = new OkHttpClient.Builder().authenticator((route, response) -> {
            if (response.request().header("Authorization") != null)
                return null; //already attempted auth;

            return response.request().newBuilder()
                    .header("Authorization", api_key)
                    .build();

        }).build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://newsapi.org/v2/top-headlines?country=us").newBuilder();
        try{
            urlBuilder.addQueryParameter("category", params[0]);
        }catch(Exception e){
        }
        String url = urlBuilder.build().toString();

        Request req = new Request.Builder()
                .url(url)
                .build();

        try(Response res = client.newCall(req).execute()){
            return res.body().string();
        }
    }

    @Override
    protected void onPostExecute(NewsResponse res) {
        if(articles != null){
            adapter = new NewsAdapter(articles, weakReference.get().getContext());
            recyclerView.setAdapter(adapter);
    //        adapter.notifyDataSetChanged();
    //        Log.d("MyTag", String.valueOf(adapter.getItemCount()));
            super.onPostExecute(res);
        }
    }

    @Override
    protected void onPreExecute() {
        View view = weakReference.get();
        if(view != null){
            recyclerView = view.findViewById(R.id.my_recycler_view);

            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(view.getContext());
            recyclerView.setLayoutManager(layoutManager);
            appDatabase = AppDatabase.getDatabase(view.getContext());
        }
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        Log.d("MyTag", values[0]);
        super.onProgressUpdate(values);
    }
}
