package me.gilo.starter.common

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.util.Objects


/**
 * A generic RecyclerView adapter that uses Data Binding & DiffUtil.
 *
 * @param <T> Type of the items in the list
 * @param <V> The of the ViewDataBinding
</V></T> */
abstract class DataListAdapter<T, V : ViewDataBinding>(listener: OnItemClickedListener<T>) :
    RecyclerView.Adapter<DataViewHolder<V>>() {

    protected val listener: OnItemClickedListener<T> = Objects.requireNonNull(listener, "listener cannot be null")
    private var items: List<T>? = null
    // each time data is set, we update this variable so that if DiffUtil calculation returns
    // after repetitive updates, we can ignore the old calculation
    private var dataVersion = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder<V> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = createBinding(inflater, parent)
        return DataViewHolder(binding)
    }

    protected abstract fun createBinding(inflater: LayoutInflater, parent: ViewGroup): V

    override fun onBindViewHolder(holder: DataViewHolder<V>, position: Int) {

        bind(holder.binding, items!![position])
        holder.binding.executePendingBindings()
    }

    @SuppressLint("StaticFieldLeak")
    @MainThread
    fun replace(update: List<T>?) {
        dataVersion++
        if (items == null) {
            if (update == null) {
                return
            }
            items = update
            notifyDataSetChanged()
        } else if (update == null) {
            val oldSize = items!!.size
            items = null
            notifyItemRangeRemoved(0, oldSize)
        } else {
            val startVersion = dataVersion
            val oldItems = items
            object : AsyncTask<Void, Void, DiffUtil.DiffResult>() {
                override fun doInBackground(vararg voids: Void): DiffUtil.DiffResult {
                    return DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                        override fun getOldListSize(): Int {
                            return oldItems!!.size
                        }

                        override fun getNewListSize(): Int {
                            return update.size
                        }

                        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                            val oldItem = oldItems!![oldItemPosition]
                            val newItem = update[newItemPosition]
                            return /*Objects.equals(oldItem, newItem)*/ false
                        }

                        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                            val oldItem = oldItems!![oldItemPosition]
                            val newItem = update[newItemPosition]
                            return /*Objects.equals(oldItem, newItem)*/ false
                        }
                    })
                }

                override fun onPostExecute(diffResult: DiffUtil.DiffResult) {
                    if (startVersion != dataVersion) {
                        // ignore update
                        return
                    }
                    items = update
                    diffResult.dispatchUpdatesTo(this@DataListAdapter)

                }
            }.execute()
        }
    }

    protected abstract fun bind(binding: V, item: T)

    protected fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    protected fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }


    override fun getItemCount(): Int {
        return if (items == null) 0 else items!!.size
    }
}
