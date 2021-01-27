package com.example.appodealtestassignment.domain.entities;

import java.util.ArrayList;
import java.util.List;

public class NewsHandler {
    private static NewsHandler instance;
    private List<RssNews> data;

    public static NewsHandler getInstance() {
        if (instance == null)
            instance = new NewsHandler();
        return instance;
    }

    private NewsHandler() {
        data = new ArrayList<>();
    }

    public void removeNews(RssNews news) {
        data.remove(news);
    }

    public List<RssNews> getData() {
        return data;
    }

    public void updateNews(RssNews news) {
        updateNewsInList(data, news);
    }

    public void setData(List<RssNews> data) {
        mergeData(this.data, data);
    }

    private void updateNewsInList(List<RssNews> list, RssNews news) {
        if (list.contains(news)) {
            int tempIndex = list.indexOf(news);
            list.remove(tempIndex);
            list.add(tempIndex, news);
        }
    }

    private void mergeData(List<RssNews> old, List<RssNews> updated) {
        for (int i = 0; i < updated.size(); i++) {
            if (old.contains(updated.get(i))) {
                mergeItem(old.get(old.indexOf(updated.get(i))), updated.get(i));
                continue;
            }
            old.add(updated.get(i));
        }
        this.data = old;
    }

    private void mergeItem(RssNews oldNews, RssNews updatedNews) {
        if (!oldNews.wasRead())
            oldNews.setWasRead(updatedNews.wasRead());
        if (!oldNews.wasHidden())
            oldNews.setWasHidden(updatedNews.wasHidden());
    }

    public List<RssNews> getDataByType(int newsType) {
        List<RssNews> answer = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getNewsType() == newsType) {
                answer.add(data.get(i));
            }
        }
        return answer;
    }
}
