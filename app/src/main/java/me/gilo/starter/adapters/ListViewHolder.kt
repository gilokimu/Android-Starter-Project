package me.gilo.starter.adapters

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ListViewHolder<T>(internal var context: Context, private var itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    abstract fun renderView(data: T)

}

