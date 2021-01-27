package com.example.appodealtestassignment.data.db;

import androidx.room.Room;

import com.example.appodealtestassignment.AndroidApplication;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

import static com.example.appodealtestassignment.rx_helper.CompletableSubscriber.subscribe;

public class DBRequestMaker implements DBRequestMakerInt {
    private NewsDB db;
    private NewsDAO dao;

    public DBRequestMaker() {
        initDB();
    }

    public Flowable<List<RssNewsDBEntity>> getFullData() {
        return dao.getAll();
    }

    public Flowable<List<RssNewsDBEntity>> getNewsByUIType(int parameter) {
        return dao.getNewsByUIType(parameter);
    }

    public Completable insertAll(RssNewsDBEntity... news) {
        return subscribe(Completable.fromRunnable(() -> {
            dao.insertAll(news);
        }));
    }

    public Completable update(RssNewsDBEntity news) {
        return subscribe(Completable.fromRunnable(() -> {
            dao.update(news);
        }));
    }

    public Completable delete(RssNewsDBEntity news) {
        return subscribe(Completable.fromRunnable(() -> {
            dao.delete(news);
        }));
    }

    private NewsDB getDataBase() {
        NewsDB db = Room.databaseBuilder(AndroidApplication.getInstance().getApplicationContext(),
                NewsDB.class, "RssNewsDBEntity").build();
        return db;
    }

    private void initDB() {
        db = getDataBase();
        dao = db.NewsDAO();
    }
}
