package com.example.appodealtestassignment.data.repositories;

import com.example.appodealtestassignment.data.db.RssNewsDBEntity;
import com.example.appodealtestassignment.data.web.RssNewsNetworkEntity;
import com.example.appodealtestassignment.domain.entities.RssNews;

import java.util.ArrayList;
import java.util.List;

public class EntityParser {

    static List<RssNews> convertNetworkData(RssNewsNetworkEntity rssNews, int newsType) {
        List<RssNews> answer = new ArrayList<>();
        RssNews news = null;
        for (int i = 0; i < rssNews.getChannel().items.size(); i++) {
            news = new RssNews();
            news.setChannelTitle(rssNews.getChannel().channelTitle);
            news.setLastBuildDate(rssNews.getChannel().lastBuildDate);
            news.setItemDescription(rssNews.getChannel().items.get(i).itemDescription);
            news.setItemLink(rssNews.getChannel().items.get(i).itemLink);
            news.setItemPubDate(rssNews.getChannel().items.get(i).itemPpubDate);
            news.setItemTitle(rssNews.getChannel().items.get(i).itemTitle);
            news.setNewsType(newsType);
            answer.add(news);
        }
        return answer;
    }

    static List<RssNews> convertDBDataList(List<RssNewsDBEntity> rssNews) {
        List<RssNews> answer = new ArrayList<>();
        RssNews news = null;
        for (int i = 0; i < rssNews.size(); i++) {
            news = new RssNews();
            news.setChannelTitle(rssNews.get(i).channelTitle);
            news.setLastBuildDate(rssNews.get(i).lastBuildDate);
            news.setItemDescription(rssNews.get(i).itemDescription);
            news.setItemLink(rssNews.get(i).itemLink);
            news.setItemPubDate(rssNews.get(i).itemPubDate);
            news.setItemTitle(rssNews.get(i).itemTitle);
            news.setNewsType(rssNews.get(i).utType);
            news.setWasRead(rssNews.get(i).wasRead);
            news.setId(rssNews.get(i).id);
            answer.add(news);
        }
        return answer;
    }

    static RssNewsDBEntity[] convertEntityListToDBData(List<RssNews> rssNews) {
        List<RssNewsDBEntity> tempAnswer = new ArrayList<>();
        RssNewsDBEntity news = null;
        for (int i = 0; i < rssNews.size(); i++) {
            news = new RssNewsDBEntity();
            news.setChannelTitle(rssNews.get(i).getChannelTitle());
            news.setLastBuildDate(rssNews.get(i).getLastBuildDate());
            news.setItemDescription(rssNews.get(i).getItemDescription());
            news.setItemLink(rssNews.get(i).getItemLink());
            news.setItemPubDate(rssNews.get(i).getItemPubDate());
            news.setItemTitle(rssNews.get(i).getItemTitle());
            news.setUtType(rssNews.get(i).getNewsType());
            news.setWasRead(rssNews.get(i).wasRead());
            news.setId(i);
            tempAnswer.add(news);
        }
        RssNewsDBEntity[] answer = new RssNewsDBEntity[tempAnswer.size()];
        tempAnswer.toArray(answer);
        return answer;
    }

    static RssNewsDBEntity convertEntityToDBData(RssNews rssNews) {
        RssNewsDBEntity news = new RssNewsDBEntity();
        news.setChannelTitle(rssNews.getChannelTitle());
        news.setLastBuildDate(rssNews.getLastBuildDate());
        news.setItemDescription(rssNews.getItemDescription());
        news.setItemLink(rssNews.getItemLink());
        news.setItemPubDate(rssNews.getItemPubDate());
        news.setItemTitle(rssNews.getItemTitle());
        news.setUtType(rssNews.getNewsType());
        news.setWasRead(rssNews.wasRead());
        news.setId(rssNews.getId());
        return news;
    }
}
