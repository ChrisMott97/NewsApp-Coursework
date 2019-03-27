package com.chrism.news;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class HTTPHandler extends AsyncTask<String ,String, NewsResponse> {

    private static final String api_key = "c97d71b1e50a454b8987b61269b52f30";
    WeakReference<Activity> weakReference;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Article> articles;

    public HTTPHandler(Activity activity){
        weakReference = new WeakReference<>(activity);
    }

    @Override
    protected NewsResponse doInBackground(String... params) {
        try{
            String json = fetch();
            Moshi moshi = new Moshi.Builder().build();
            JsonAdapter<NewsResponse> jsonAdapter = moshi.adapter(NewsResponse.class);
            return jsonAdapter.fromJson(json);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }


    }

    private String fetch() throws IOException{
        OkHttpClient client = new OkHttpClient.Builder().authenticator((route, response) -> {
            if (response.request().header("Authorization") != null)
                return null; //already attempted auth;

            return response.request().newBuilder()
                    .header("Authorization", api_key)
                    .build();

        }).build();

        Request req = new Request.Builder()
                .url("https://newsapi.org/v2/top-headlines?country=us")
                .build();

        try(Response res = client.newCall(req).execute()){
            return res.body().string();
        }
    }

    @Override
    protected void onPostExecute(NewsResponse res) {
        articles = res.getArticles();
        adapter = new NewsAdapter(articles);
        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//        Log.d("MyTag", String.valueOf(adapter.getItemCount()));
        super.onPostExecute(res);
    }

    @Override
    protected void onPreExecute() {
        Activity activity = weakReference.get();
        if(activity != null){
            recyclerView = activity.findViewById(R.id.my_recycler_view);

            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(activity);
            recyclerView.setLayoutManager(layoutManager);
        }
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        Log.d("MyTag", values[0]);
        super.onProgressUpdate(values);
    }
}
