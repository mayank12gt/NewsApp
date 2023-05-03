package com.example.newsreader.network;

import com.example.newsreader.models.NewsArticleResponse;


import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

@GET("top-headlines")
Single<NewsArticleResponse> getTopNews(@Query("page")int page, @Query("country") String country, @Query("apiKey") String api_key);
}
