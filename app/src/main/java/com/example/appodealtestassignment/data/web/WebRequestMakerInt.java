package com.example.appodealtestassignment.data.web;

import io.reactivex.Observable;

public interface WebRequestMakerInt {
    Observable<RssNewsNetworkEntity> getEnglandNews();

    Observable<RssNewsNetworkEntity> getWorldNews();

    Observable<RssNewsNetworkEntity> getHomeNews();
}
