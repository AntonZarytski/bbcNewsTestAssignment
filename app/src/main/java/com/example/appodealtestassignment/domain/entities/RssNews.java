package com.example.appodealtestassignment.domain.entities;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class RssNews {
    private int id;
    private String lastBuildDate;
    private String channelTitle;
    private String itemPubDate;
    private String itemTitle;
    private String itemLink;
    private String itemDescription;
    private boolean wasRead;
    private boolean wasHidden;
    private int newsType;

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getItemPubDate() {
        return itemPubDate;
    }

    public void setItemPubDate(String itemPubDate) {
        this.itemPubDate = itemPubDate;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemLink() {
        return itemLink;
    }

    public void setItemLink(String itemLink) {
        this.itemLink = itemLink;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public boolean wasHidden() {
        return wasHidden;
    }

    public void setWasHidden(boolean wasHided) {
        this.wasHidden = wasHided;
    }

    public boolean wasRead() {
        return wasRead;
    }

    public void setWasRead(boolean wasRead) {
        this.wasRead = wasRead;
    }

    public int getNewsType() {
        return newsType;
    }

    public void setNewsType(int newsType) {
        this.newsType = newsType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof RssNews) {
            return newsType == ((RssNews) o).newsType
                    && channelTitle.equalsIgnoreCase(((RssNews) o).channelTitle)
                    && itemLink.equalsIgnoreCase(((RssNews) o).itemLink)
                    && itemDescription.equalsIgnoreCase(((RssNews) o).itemDescription);
        } else return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).toHashCode();
    }
}