package com.example.parsehttp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parsehttp.R;
import com.example.parsehttp.model.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private final List<News> newsList = new ArrayList<>();
    private final OnNewsClickListener onNewsClickListener;

    public interface OnNewsClickListener {
        void onNewsClick(News news);
    }

    public NewsAdapter(OnNewsClickListener onNewsClickListener) {
        this.onNewsClickListener = onNewsClickListener;
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageSrc;
        private final TextView title;
        private final TextView date;
        private final TextView note;

        public NewsViewHolder(View view) {
            super(view);
            imageSrc = (ImageView) view.findViewById(R.id.imageSrc);
            title = (TextView) view.findViewById(R.id.title);
            date = (TextView) view.findViewById(R.id.date);
            note = (TextView) view.findViewById(R.id.note);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = newsList.get(getLayoutPosition());
                    onNewsClickListener.onNewsClick(news);
                }
            });
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
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

    public void setItems (Collection<News> news) {
        newsList.addAll(news);
        notifyDataSetChanged();
    }

    public void clearItems() {
        newsList.clear();
        notifyDataSetChanged();
    }
}
