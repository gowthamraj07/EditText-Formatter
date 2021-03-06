package com.androidwidgets.listview.domains;

import androidx.recyclerview.widget.RecyclerView;

public interface ListItem {
    RecyclerView.ViewHolder getViewHolder();
    void bindView(RecyclerView.ViewHolder viewHolder, int position);
}
