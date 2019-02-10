package me.gilo.starter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class ListViewHolder<T> extends RecyclerView.ViewHolder{
    View itemView;
    Context context;

    public ListViewHolder(Context context, View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.context = context;
    }

    public abstract void renderView(T data);

}

