package com.phongbm.jsoup;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoryManager {
    private static final String TAG = "StoryManager";

    public StoryManager() {
    }

    public void fetchStoryData(OnStoryFetchListener listener) {
        StoryAsyncTask storyAsyncTask = new StoryAsyncTask(listener);
        storyAsyncTask.execute("http://truyencotich.vn/truyen-dan-gian");
    }

    private class StoryAsyncTask extends AsyncTask<String, Void, List<Story>> {
        private OnStoryFetchListener listener;

        public StoryAsyncTask(OnStoryFetchListener listener) {
            this.listener = listener;
        }

        @Override
        protected List<Story> doInBackground(String... urls) {
            List<Story> stories = new ArrayList<>();
            try {
                Document document = Jsoup.connect(urls[0]).get();
                Element siteContent = document.select("div.site-content").get(0);
                Elements articles = siteContent.getElementsByTag("article");
                for (Element article: articles) {
                    String imageUrl = article.getElementsByTag("img").attr("src");
                    Element entryContent = article.select("div.entry-content").first();
                    String url = entryContent.getElementsByTag("a").attr("href");
                    String title = entryContent.getElementsByTag("a").text();
                    String content = entryContent.select("p.post-excerpt").text();

                    stories.add(new Story(title, content, imageUrl, url));
                }
            } catch (IOException e) {
                e.printStackTrace();
                listener.onFailure();
            }
            return stories;
        }

        @Override
        protected void onPostExecute(List<Story> stories) {
            listener.onSuccess(stories);
        }
    }

    public interface OnStoryFetchListener {
        void onSuccess(List<Story> stories);

        void onFailure();
    }



}