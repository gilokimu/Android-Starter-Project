package me.gilo.starter.common

import androidx.lifecycle.LiveData
import com.google.firebase.firestore.*
import me.gilo.starter.models.Model

import java.util.ArrayList


class QueryLiveData<T : Model>(private val query: Query, private val type: Class<T>) : LiveData<Resource<List<T>>>(),
    EventListener<QuerySnapshot> {
    private var registration: ListenerRegistration? = null

    init {

        value = Resource(Status.LOADING)
    }

    override fun onEvent(snapshots: QuerySnapshot?, e: FirebaseFirestoreException?) {
        if (e != null) {
            value = Resource(e)
            return
        }
        value = Resource(documentToList(snapshots!!))
    }

    override fun onActive() {
        super.onActive()
        registration = query.addSnapshotListener(this)
    }

    override fun onInactive() {
        super.onInactive()
        if (registration != null) {
            registration!!.remove()
            registration = null
        }
    }

    private fun documentToList(snapshots: QuerySnapshot): List<T> {
        val retList = ArrayList<T>()
        if (snapshots.isEmpty) {
            return retList
        }

        for (document in snapshots.documents) {
            retList.add(document.toObject(type)!!.withId(document.id))
        }

        return retList
    }
}
