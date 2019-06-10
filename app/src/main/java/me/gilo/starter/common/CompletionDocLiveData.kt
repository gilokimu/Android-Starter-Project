package me.gilo.starter.common


import androidx.lifecycle.LiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference


class CompletionDocLiveData : LiveData<Resource<Boolean>>(), OnCompleteListener<DocumentReference> {
    init {
        value = Resource(Status.LOADING)
    }

    override fun onComplete(task: Task<DocumentReference>) {
        if (task.isSuccessful) {
            value = Resource(true)
        } else {
            value = Resource(task.exception!!)
        }
    }
}
