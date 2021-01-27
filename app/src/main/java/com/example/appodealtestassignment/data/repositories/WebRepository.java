package com.example.appodealtestassignment.data.repositories;

import android.util.Log;

import com.example.appodealtestassignment.data.web.RssNewsNetworkEntity;
import com.example.appodealtestassignment.data.web.WebRequestMaker;
import com.example.appodealtestassignment.data.web.WebRequestMakerInt;
import com.example.appodealtestassignment.rx_helper.ObservableSubscriber;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.example.appodealtestassignment.data.NewsTypeEnum.ENGLAND_NEWS;
import static com.example.appodealtestassignment.data.NewsTypeEnum.HOME_NEWS;
import static com.example.appodealtestassignment.data.NewsTypeEnum.WORLD_NEWS;

public class WebRepository {
    private final static String TAG = WebRepository.class.getSimpleName();
    private WebRequestMakerInt requestMaker;
    private DataSetter dataSetter;
    private Disposable homeNewsRequest;
    private Disposable englandNewsRequest;
    private Disposable worldNewsRequest;

    public WebRepository(DataSetter dataSetter) {
        requestMaker = new WebRequestMaker();
        this.dataSetter = dataSetter;
    }

    public void getEnglandNews() {
        ObservableSubscriber.getObservable(requestMaker.getEnglandNews()).subscribe(new Observer<RssNewsNetworkEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                englandNewsRequest = d;
            }

            @Override
            public void onNext(RssNewsNetworkEntity rssNews) {
                dataSetter.setDataFromNetwork(EntityParser.convertNetworkData(rssNews, ENGLAND_NEWS), ENGLAND_NEWS);
                Log.d(TAG, "onNext: " + rssNews.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e);
            }

            @Override
            public void onComplete() {
                englandNewsRequest.dispose();
            }
        });
        ;
    }

    public void getWorldNews() {
        ObservableSubscriber.getObservable(requestMaker.getWorldNews()).subscribe(new Observer<RssNewsNetworkEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                worldNewsRequest = d;
            }

            @Override
            public void onNext(RssNewsNetworkEntity rssNews) {
                dataSetter.setDataFromNetwork(EntityParser.convertNetworkData(rssNews, WORLD_NEWS), WORLD_NEWS);
                Log.d(TAG, "onNext: " + rssNews.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e);
            }

            @Override
            public void onComplete() {
                worldNewsRequest.dispose();
            }
        });
        ;
    }

    public void getHomeNews() {
        ObservableSubscriber.getObservable(requestMaker.getHomeNews()).subscribe(new Observer<RssNewsNetworkEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                homeNewsRequest = d;
            }

            @Override
            public void onNext(RssNewsNetworkEntity rssNews) {
                dataSetter.setDataFromNetwork(EntityParser.convertNetworkData(rssNews, HOME_NEWS), HOME_NEWS);
                Log.d(TAG, "onNext: " + rssNews.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e);
            }

            @Override
            public void onComplete() {
                homeNewsRequest.dispose();
            }
        });
    }
}
