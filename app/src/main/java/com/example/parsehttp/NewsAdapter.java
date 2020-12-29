package com.example.parsehttp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    public List<News> newsList = new ArrayList<>();

    class NewsViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageSrc;
        private TextView title;
        private TextView date;
        private TextView note;

        public NewsViewHolder(View view) {
            super(view);
            imageSrc = (ImageView) view.findViewById(R.id.imageSrc);
            title = (TextView) view.findViewById(R.id.title);
            date = (TextView) view.findViewById(R.id.date);
            note = (TextView) view.findViewById(R.id.note);
        }

        public void bind (News news) {
            Picasso.get()
                    .load(news.getImageSrc())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imageSrc);
            title.setText(news.getTitle());
            date.setText(news.getDate());
            note.setText(news.getNote());
            imageSrc.setVisibility(news.getImageSrc() != null ? View.VISIBLE : View.GONE);
        }
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(newsList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
