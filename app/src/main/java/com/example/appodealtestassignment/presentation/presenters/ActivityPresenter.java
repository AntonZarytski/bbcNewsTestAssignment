package com.example.appodealtestassignment.presentation.presenters;

import com.example.appodealtestassignment.data.InternetConnectionStatus;
import com.example.appodealtestassignment.domain.UseCaseInputPort;
import com.example.appodealtestassignment.domain.UseCases;
import com.example.appodealtestassignment.domain.entities.RssNews;
import com.example.appodealtestassignment.presentation.OutputDataProvider;

import java.util.List;

public class ActivityPresenter implements OutputDataProvider {
    private ActivityView view;
    private UseCaseInputPort useCases;

    public ActivityPresenter(ActivityView view) {
        this.view = view;
        useCases = new UseCases(this);
    }

    public void stop() {
        useCases.saveNews();
    }

    @Override
    public void dataIsReady(List<RssNews> data) {
        if (data.isEmpty()) {
            if (InternetConnectionStatus.isOffline()) {
                view.checkNetworkNotification();
            } else {
                view.innerErrorNotification();
            }
        }
    }
}
