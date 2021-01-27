package com.example.appodealtestassignment.rx_helper;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

public class CompletableSubscriber {

    public static Completable subscribe(Completable completable) {
        return completable.subscribeOn(Schedulers.io());
    }
}
