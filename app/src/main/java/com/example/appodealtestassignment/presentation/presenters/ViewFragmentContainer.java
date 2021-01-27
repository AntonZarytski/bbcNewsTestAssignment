package com.example.appodealtestassignment.presentation.presenters;

import com.example.appodealtestassignment.domain.entities.RssNews;

import java.util.List;

public interface ViewFragmentContainer {
    void setViewData(List<RssNews> data);
}
