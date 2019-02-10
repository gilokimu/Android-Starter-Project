package me.gilo.starter.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public class ListAdapter<T> extends RecyclerView.Adapter<ListViewHolder<T>> {
    private List<T> data;
    ListViewHolder<T> holder;


    public ListAdapter(List<T> data, ListViewHolder<T> holder) {
        this.data = data;
        this.holder = holder;
    }

    @Override
    public ListViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        return holder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder<T> holder, int position) {
        holder.renderView(data.get(position));
    }


    @Override
    public int getItemCount() {
        return data.size() == 0 ? 0 : data.size();
    }

}
