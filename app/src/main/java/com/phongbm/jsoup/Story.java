package com.phongbm.jsoup;

public class Story {
    private String title;
    private String content;
    private String imageUrl;
    private String url;

    public Story() {
    }

    public Story(String title, String content, String imageUrl, String url) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n" +
                "Content: " + content + "\n" +
                "Image url: " + imageUrl + "\n" +
                "Url: " + url + "\n";
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUrl() {
        return url;
    }

}