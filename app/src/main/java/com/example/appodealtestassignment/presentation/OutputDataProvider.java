package com.example.appodealtestassignment.presentation;

import com.example.appodealtestassignment.domain.entities.RssNews;

import java.util.List;

public interface OutputDataProvider {
    void dataIsReady(List<RssNews> data);
}
