package com.example.appodealtestassignment.data.web;

import io.reactivex.Observable;

import static com.example.appodealtestassignment.data.web.Retrofit.retrofit;

public class WebRequestMaker implements WebRequestMakerInt {
    private ApiService apiService;

    public WebRequestMaker() {
        this.apiService = retrofit().create(ApiService.class);
    }

    public Observable<RssNewsNetworkEntity> getEnglandNews() {
        return apiService.getEnglandNews();
    }

    public Observable<RssNewsNetworkEntity> getWorldNews() {
        return apiService.getWorldNews();
    }

    public Observable<RssNewsNetworkEntity> getHomeNews() {
        return apiService.getHomeNews();
    }
}
