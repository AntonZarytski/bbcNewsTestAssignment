package com.example.appodealtestassignment.data.repositories;

import com.example.appodealtestassignment.domain.entities.RssNews;

import java.util.List;

public interface Gateway {
    void getNewsByUIType(int parameter);

    void getCahchedData(int parameter);

    void insertAll(List<RssNews> news);

    void updateItem(RssNews news);

    void deleteItem(RssNews news);
}
