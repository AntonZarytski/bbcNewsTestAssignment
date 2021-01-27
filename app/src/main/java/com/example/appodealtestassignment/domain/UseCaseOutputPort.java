package com.example.appodealtestassignment.domain;

import com.example.appodealtestassignment.domain.entities.RssNews;

import java.util.List;

public interface UseCaseOutputPort {
    void transferData(List<RssNews> data, int newsType, boolean isCache);

}
