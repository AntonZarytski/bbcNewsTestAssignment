package com.example.appodealtestassignment.data.repositories;

import com.example.appodealtestassignment.data.db.DBRequestMaker;
import com.example.appodealtestassignment.data.db.DBRequestMakerInt;
import com.example.appodealtestassignment.data.db.RssNewsDBEntity;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class DataBaseRepository {
    private DBRequestMakerInt requestMaker;
    private DataSetter dataSetter;

    public DataBaseRepository(DataSetter dataSetter) {
        requestMaker = new DBRequestMaker();
        this.dataSetter = dataSetter;
    }

    public void getNewsByUIType(int parameter) {
        requestMaker.getNewsByUIType(parameter).observeOn(AndroidSchedulers.mainThread())
                .subscribe(rssNewsDBEntities -> dataSetter.setDataFromDB(EntityParser.convertDBDataList(rssNewsDBEntities), parameter));
    }

    public void insertAll(RssNewsDBEntity... news) {
        requestMaker.insertAll(news).subscribe();
    }

    public void update(RssNewsDBEntity news) {
        requestMaker.update(news).subscribe();
    }

    public void delete(RssNewsDBEntity news) {
        requestMaker.delete(news).subscribe();
    }
}
