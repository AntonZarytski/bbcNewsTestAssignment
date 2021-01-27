package com.example.appodealtestassignment.data.db;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface DBRequestMakerInt {
    Flowable<List<RssNewsDBEntity>> getFullData();

    Flowable<List<RssNewsDBEntity>> getNewsByUIType(int parameter);

    Completable insertAll(RssNewsDBEntity... news);

    Completable update(RssNewsDBEntity news);

    Completable delete(RssNewsDBEntity news);
}
