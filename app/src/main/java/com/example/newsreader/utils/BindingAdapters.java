package com.example.newsreader.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.newsreader.R;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BindingAdapters {
    @BindingAdapter("imageURL")
    public static void loadImage(ImageView imageView,String URL){

        if(URL!=null) {
            Glide.with(imageView.getContext()).load(URL).placeholder(R.drawable.icons_newspaper_48).into(imageView);
        }
        else
        {
            Glide.with(imageView.getContext()).load(R.drawable.icons_newspaper_48).into(imageView);
        }

    }

    @BindingAdapter("setDate")
    public static void setDate(TextView textView,String publishedAt){
        DateFormat outputFormat = new SimpleDateFormat("LLL dd, yyyy", Locale.US);
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);


        Date date = null;
        try {
            date = inputFormat.parse(publishedAt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputText = outputFormat.format(date);
        textView.setText(outputText);
    }
}
