package com.example.newsreader;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.newsreader.adapter.ArticleComparator;
import com.example.newsreader.adapter.NewsArticlesAdapter;
import com.example.newsreader.models.Article;
import com.example.newsreader.viewmodels.HomeFragmentViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class HomeFragment extends Fragment implements NewsItemOnClick {

    ImageSlider imageSlider;
    RecyclerView recyclerView;
    HomeFragmentViewModel homeFragmentViewModel;
    NewsArticlesAdapter adapter;
    ProgressBar loading;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_home, container, false);


        getActivity().setTitle("Top Stories");

        //imageSlider = v.findViewById(R.id.imageslider);
        //setupImageslider();
           recyclerView=v.findViewById(R.id.topnewsrv);
        loading = v.findViewById(R.id.loading);

           homeFragmentViewModel=new ViewModelProvider(this).get(HomeFragmentViewModel.class);
           adapter = new NewsArticlesAdapter(new ArticleComparator(), getActivity().getParent(),this);
           recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                loading.setVisibility(View.VISIBLE);
                homeFragmentViewModel.articlepagingdataflowable.subscribe(articlePagingData -> {
                    adapter.submitData(getLifecycle(),articlePagingData);});

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "Ok", Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.INVISIBLE);

                    }
                });

            }
        });
            return v;
    }


    @Override
    public void onItemClicked(Article article, int position) {

        Bundle bundle = new Bundle();
        bundle.putString("url",article.getUrl());
        WebViewFragment fragment = new WebViewFragment();
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.load_frag,fragment).commit();
    }
}