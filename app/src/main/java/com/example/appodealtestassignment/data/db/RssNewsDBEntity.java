package com.example.appodealtestassignment.data.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RssNewsDBEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "ui_type")
    public int utType;
    @ColumnInfo(name = "lastBuildDate")
    public String lastBuildDate;
    @ColumnInfo(name = "channel_title")
    public String channelTitle;
    @ColumnInfo(name = "item_pubDate")
    public String itemPubDate;
    @ColumnInfo(name = "item_title")
    public String itemTitle;
    @ColumnInfo(name = "item_link")
    public String itemLink;
    @ColumnInfo(name = "item_description")
    public String itemDescription;
    @ColumnInfo(name = "was_read")
    public boolean wasRead;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtType() {
        return utType;
    }

    public void setUtType(int utType) {
        this.utType = utType;
    }

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

    public boolean isWasRead() {
        return wasRead;
    }

    public void setWasRead(boolean wasRead) {
        this.wasRead = wasRead;
    }
}

