package com.phongbm.jsoup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Story> stories;

    public StoryAdapter(Context context, List<Story> stories) {
        inflater = LayoutInflater.from(context);
        this.stories = stories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_story, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Story story = stories.get(position);

        Glide.with(holder.itemView.getContext())
                .load(story.getImageUrl())
                .into(holder.imgAvatar);

        holder.txtTitle.setText(story.getTitle());
        holder.txtContent.setText(story.getContent());
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public void updateData(List<Story> stories) {
        this.stories.clear();
        this.stories.addAll(stories);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAvatar;
        private TextView txtTitle;
        private TextView txtContent;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAvatar =  itemView.findViewById(R.id.img_avatar);
            txtTitle =  itemView.findViewById(R.id.txt_title);
            txtContent =  itemView.findViewById(R.id.txt_content);
        }
    }
}