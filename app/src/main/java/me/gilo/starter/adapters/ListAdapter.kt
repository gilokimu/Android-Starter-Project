package me.gilo.starter.adapters


import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListAdapter<T>(private val data: List<T>, private var holder: ListViewHolder<T>) :
    RecyclerView.Adapter<ListViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder<T> {
        return holder
    }

    override fun onBindViewHolder(holder: ListViewHolder<T>, position: Int) {
        holder.renderView(data[position])
    }


    override fun getItemCount(): Int {
        return if (data.isEmpty()) 0 else data.size
    }

}
