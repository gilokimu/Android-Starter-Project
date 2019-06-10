package me.gilo.localdataprovider.utils


import androidx.recyclerview.widget.DiffUtil
import me.gilo.localdataprovider.models.NoteEntity


class NoteDiffUtil(internal var oldNoteEntityList: List<NoteEntity>, internal var newNoteEntityList: List<NoteEntity>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldNoteEntityList.size
    }

    override fun getNewListSize(): Int {
        return newNoteEntityList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNoteEntityList[oldItemPosition].id == newNoteEntityList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNoteEntityList[oldItemPosition] == newNoteEntityList[newItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}
