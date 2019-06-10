package me.gilo.starter.common


import androidx.lifecycle.LiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task


class CompletionLiveListData<T> : LiveData<Resource<List<T>>>(), OnCompleteListener<List<T>> {
    init {
        value = Resource(Status.LOADING)
    }

    override fun onComplete(task: Task<List<T>>) {
        if (task.isSuccessful) {
            setValue(Resource(task.result!!))
        } else {
            setValue(Resource(task.exception!!))
        }
    }
}
