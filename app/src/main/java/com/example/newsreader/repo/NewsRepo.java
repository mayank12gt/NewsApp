package com.example.newsreader.repo;

import androidx.lifecycle.ViewModel;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;

import com.example.newsreader.models.Article;
import com.example.newsreader.network.APIClient;
import com.example.newsreader.network.APIService;
import com.example.newsreader.network.NewsArticlesPagingSource;

import io.reactivex.rxjava3.core.Flowable;

public class NewsRepo extends ViewModel {

    APIService apiService;


    public NewsRepo() {
        apiService = APIClient.getRetrofit().create(APIService.class);
    }


    public Pager<Integer, Article> getTopNews(String country, String api_key) {
        NewsArticlesPagingSource PagingSource = new NewsArticlesPagingSource(country, api_key);

        // Create new Pager
        Pager<Integer, Article> pager = new Pager(
                // Create new paging config
                new PagingConfig(10, //  Count of items in one page
                        20, //  Number of items to prefetch
                        false, // Enable placeholders for data which is not yet loaded
                        20, // initialLoadSize - Count of items to be loaded initially
                        20 * 499),// maxSize - Count of total items to be shown in recyclerview
                () -> PagingSource); // set paging source

        // inti Flowable
        return pager;

    }
}
