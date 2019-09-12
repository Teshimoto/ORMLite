package com.example.ormlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Article> data;

    public RecyclerViewAdapter(Context context, FakeData fakeData) {
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(android.R.layout.simple_list_item_2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.articleName.setText(data.get(position).getArticleName());
        holder.articleText.setText(data.get(position).getArticleText());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView articleName;
        TextView articleText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            articleName = itemView.findViewById(android.R.id.text1);
            articleText = itemView.findViewById(android.R.id.text2);
        }
    }

    public void setData(final List<Article> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
