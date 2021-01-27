package com.example.appodealtestassignment.domain;

import com.example.appodealtestassignment.data.InternetConnectionStatus;
import com.example.appodealtestassignment.data.repositories.DataGateway;
import com.example.appodealtestassignment.data.repositories.Gateway;
import com.example.appodealtestassignment.domain.entities.NewsHandler;
import com.example.appodealtestassignment.domain.entities.RssNews;
import com.example.appodealtestassignment.presentation.OutputDataProvider;

import java.util.List;

public class UseCases implements UseCaseOutputPort, UseCaseInputPort {
    private NewsHandler entity;
    private Gateway dataGateway;
    private OutputDataProvider dataProvider;

    public UseCases(OutputDataProvider dataProvider) {
        dataGateway = new DataGateway(this);
        this.dataProvider = dataProvider;
        entity = NewsHandler.getInstance();
    }

    @Override
    public void transferData(List<RssNews> data, int newsType, boolean isCache) {
        entity.setData(data);
        if (InternetConnectionStatus.isOnline()) {
            if (!isCache)
                dataProvider.dataIsReady(entity.getDataByType(newsType));
        } else {
            dataProvider.dataIsReady(entity.getDataByType(newsType));
        }
    }

    @Override
    public void saveNews() {
        dataGateway.insertAll(entity.getData());
    }

    @Override
    public void changeData(RssNews news) {
        entity.updateNews(news);
    }

    @Override
    public void getNewsByParam(int param) {
        dataGateway.getNewsByUIType(param);
    }

    @Override
    public void getCahchedData(int param) {
        dataGateway.getCahchedData(param);
    }
}
