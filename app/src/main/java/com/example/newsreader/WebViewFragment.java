package com.example.newsreader;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.model.UrlUriLoader;

import java.util.concurrent.Executors;


public class WebViewFragment extends Fragment {



WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

     View v= inflater.inflate(R.layout.fragment_web_view, container, false);
     webView = v.findViewById(R.id.webview);

     Bundle bundle = this.getArguments();
     if(bundle!=null){
         Toast.makeText(getContext(), "Webview", Toast.LENGTH_SHORT).show();
         webView.setWebViewClient(new WebViewClient());

         webView.loadUrl(bundle.getString("url"));
     }







     return v;
    }
}