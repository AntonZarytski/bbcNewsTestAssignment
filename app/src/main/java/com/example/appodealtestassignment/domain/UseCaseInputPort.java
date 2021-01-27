package com.example.appodealtestassignment.domain;

import com.example.appodealtestassignment.domain.entities.RssNews;

public interface UseCaseInputPort {
    void getNewsByParam(int param);

    void getCahchedData(int param);

    void saveNews();

    void changeData(RssNews news);
}
