package com.phongbm.jsoup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements StoryManager.OnStoryFetchListener {
    private RecyclerView rcvStory;
    private StoryAdapter storyAdapter;

    private StoryManager storyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvStory = (RecyclerView) findViewById(R.id.rcv_story);
        rcvStory.setLayoutManager(new GridLayoutManager(this, 2));

        List<Story> stories = new ArrayList<>();
        storyAdapter = new StoryAdapter(this, stories);
        rcvStory.setAdapter(storyAdapter);

        storyManager = new StoryManager();
        storyManager.fetchStoryData(this);
    }

    @Override
    public void onSuccess(List<Story> stories) {
        storyAdapter.updateData(stories);
    }

    @Override
    public void onFailure() {
    }

}