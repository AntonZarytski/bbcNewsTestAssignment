package com.example.appodealtestassignment.data.web;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/news/england/rss.xml")
    Observable<RssNewsNetworkEntity> getEnglandNews();

    @GET("/news/world/rss.xml")
    Observable<RssNewsNetworkEntity> getWorldNews();

    @GET("/news/rss.xml")
    Observable<RssNewsNetworkEntity> getHomeNews();
}
