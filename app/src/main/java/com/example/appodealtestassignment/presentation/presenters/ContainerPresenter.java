package com.example.appodealtestassignment.presentation.presenters;

import com.example.appodealtestassignment.domain.UseCaseInputPort;
import com.example.appodealtestassignment.domain.UseCases;
import com.example.appodealtestassignment.domain.entities.RssNews;
import com.example.appodealtestassignment.presentation.OutputDataProvider;
import com.example.appodealtestassignment.presentation.ui.fragments.FragmentNewsContainer;

import java.util.List;

public class ContainerPresenter implements OutputDataProvider {
    ViewFragmentContainer view;
    private UseCaseInputPort useCases;

    public ContainerPresenter(FragmentNewsContainer view, int viewType) {
        this.view = view;
        useCases = new UseCases(this);
        useCases.getCahchedData(viewType);
    }

    public void changeData(RssNews news) {
        useCases.changeData(news);
    }

    public void updateData(int param) {
        useCases.getNewsByParam(param);
    }

    public void getDataByParameter(int param) {
        useCases.getNewsByParam(param);
    }

    @Override
    public void dataIsReady(List<RssNews> data) {
        view.setViewData(data);
    }
}
