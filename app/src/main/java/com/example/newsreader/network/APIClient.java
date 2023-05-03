package com.example.newsreader.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static Retrofit retrofit;

    public static synchronized Retrofit getRetrofit(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl("https://newsapi.org/v2/").addCallAdapterFactory(RxJava3CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
        }
       return retrofit;
    }
}
