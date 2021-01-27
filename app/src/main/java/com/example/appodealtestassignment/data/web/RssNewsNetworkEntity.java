package com.example.appodealtestassignment.data.web;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class RssNewsNetworkEntity {
    @Element(name = "channel", required = false)
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Root(name = "image")
    public static class Image {
        @Element(name = "url", required = false)
        public String url;
        @Element(name = "title", required = false)
        public String title;
        @Element(name = "link", required = false)
        public String link;
    }

    @Root(name = "guid")
    public static class Guid {
        @Attribute(name = "isPermaLink", required = false)
        public boolean isPermaLink;
        @Element(name = "title", required = false)
        public String text;
    }

    @Root(name = "item")
    public static class Item {
        @Element(name = "title", required = false)
        public String itemTitle;
        @Element(name = "description", required = false)
        public String itemDescription;
        @Element(name = "link", required = false)
        public String itemLink;
        @Element(name = "guid", required = false)
        public Guid guid;
        @Element(name = "pubDate", required = false)
        public String itemPpubDate;
    }

    @Root(name = "channel")
    public static class Channel {
        @Element(name = "title", required = false)
        public String channelTitle;
        @Element(name = "description", required = false)
        public String description;
        @Element(name = "link", required = false)
        public String link;
        @Element(name = "image", required = false)
        public Image image;
        @Element(name = "generator", required = false)
        public String generator;
        @Element(name = "lastBuildDate", required = false)
        public String lastBuildDate;
        @Element(name = "copyright", required = false)
        public String copyright;
        @Element(name = "language", required = false)
        public String language;
        @Element(name = "ttl", required = false)
        public int ttl;
        @ElementList(inline = true, required = false)
        public List<Item> items;
    }

    @Root(name = "rss")
    public static class Rss {
        @Element(name = "channel", required = false)
        public Channel channel;
        @Element(name = "dc", required = false)
        public String dc;
        @Element(name = "content", required = false)
        public String content;
        @Element(name = "atom", required = false)
        public String atom;
        @Attribute(name = "version", required = false)
        public double version;
        @Element(name = "media", required = false)
        public String media;
        @Element(name = "text", required = false)
        public String text;
    }
}
