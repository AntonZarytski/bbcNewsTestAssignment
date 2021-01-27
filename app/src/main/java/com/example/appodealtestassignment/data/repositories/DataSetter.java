package com.example.appodealtestassignment.data.repositories;

import com.example.appodealtestassignment.domain.entities.RssNews;

import java.util.List;

public interface DataSetter {
    void setDataFromNetwork(List<RssNews> data, int newsType);

    void setDataFromDB(List<RssNews> data, int newsType);
}
