package com.example.appodealtestassignment.data.repositories;

import com.example.appodealtestassignment.data.InternetConnectionStatus;
import com.example.appodealtestassignment.domain.UseCaseOutputPort;
import com.example.appodealtestassignment.domain.entities.RssNews;

import java.util.List;

import static com.example.appodealtestassignment.presentation.ui.activities.NewsTypeEnum.ENGLAND_NEWS;
import static com.example.appodealtestassignment.presentation.ui.activities.NewsTypeEnum.HOME_NEWS;
import static com.example.appodealtestassignment.presentation.ui.activities.NewsTypeEnum.WORLD_NEWS;

public class DataGateway implements DataSetter, Gateway {
    private WebRepository webRepository;
    private DataBaseRepository dataBaseRepository;
    private UseCaseOutputPort useCaseOutputPort;

    public DataGateway(UseCaseOutputPort useCaseOutputPort) {
        webRepository = new WebRepository(this);
        dataBaseRepository = new DataBaseRepository(this);
        this.useCaseOutputPort = useCaseOutputPort;
    }

    @Override
    public void getCahchedData(int parameter) {
        dataBaseRepository.getNewsByUIType(parameter);
    }

    @Override
    public void getNewsByUIType(int parameter) {
        if (InternetConnectionStatus.isOnline()) {
            switch (parameter) {
                case ENGLAND_NEWS:
                    webRepository.getEnglandNews();
                    break;
                case HOME_NEWS:
                    webRepository.getHomeNews();
                    break;
                case WORLD_NEWS:
                    webRepository.getWorldNews();
            }
        }
    }

    @Override
    public void insertAll(List<RssNews> news) {
        dataBaseRepository.insertAll(EntityParser.convertEntityListToDBData(news));
    }

    @Override
    public void updateItem(RssNews news) {
        dataBaseRepository.update(EntityParser.convertEntityToDBData(news));
    }

    @Override
    public void deleteItem(RssNews news) {
        dataBaseRepository.delete(EntityParser.convertEntityToDBData(news));
    }

    @Override
    public void setDataFromNetwork(List<RssNews> data, int newsType) {
        useCaseOutputPort.transferData(data, newsType, false);
    }

    @Override
    public void setDataFromDB(List<RssNews> data, int newsType) {
        useCaseOutputPort.transferData(data, newsType, true);
    }
}
