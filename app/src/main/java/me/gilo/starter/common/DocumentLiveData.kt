package me.gilo.starter.common


import androidx.lifecycle.LiveData
import com.google.firebase.firestore.*


class DocumentLiveData<T>(private val ref: DocumentReference, private val type: Class<T>) : LiveData<Resource<T>>(),
    EventListener<DocumentSnapshot> {
    private var registration: ListenerRegistration? = null

    override fun onEvent(snapshot: DocumentSnapshot?, e: FirebaseFirestoreException?) {
        if (e != null) {
            value = Resource(e)
            return
        }
        value = Resource(snapshot!!.toObject(type)!!)
    }

    override fun onActive() {
        super.onActive()
        registration = ref.addSnapshotListener(this)
    }

    override fun onInactive() {
        super.onInactive()
        if (registration != null) {
            registration!!.remove()
            registration = null
        }
    }
}
