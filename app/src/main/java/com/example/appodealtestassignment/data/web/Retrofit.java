package com.example.appodealtestassignment.data.web;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class Retrofit {
    private static final String BASE_URL = "http://feeds.bbci.co.uk";

    static retrofit2.Retrofit retrofit() {
        return new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(new OkHttpClient.Builder().build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
    }
}
