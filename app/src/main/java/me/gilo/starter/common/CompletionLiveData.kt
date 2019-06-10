package me.gilo.starter.common


import androidx.lifecycle.LiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task


class CompletionLiveData : LiveData<Resource<Boolean>>(), OnCompleteListener<Void> {
    init {
        value = Resource(Status.LOADING)
    }

    override fun onComplete(task: Task<Void>) {
        if (task.isSuccessful) {
            value = Resource(true)
        } else {
            value = Resource(task.exception!!)
        }
    }
}
