package me.gilo.localdataprovider.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import me.gilo.core.entity.Note
import me.gilo.localdataprovider.utils.TimestampConverter


import java.io.Serializable
import java.util.Date
import kotlin.concurrent.timerTask

@Entity
data class NoteEntity (

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var title: String? = null,

    var description: String? = null,

    @ColumnInfo(name = "created_at")
    @TypeConverters(TimestampConverter::class)
    var createdAt: Date? = null,

    @ColumnInfo(name = "modified_at")
    @TypeConverters(TimestampConverter::class)
    var modifiedAt: Date? = null,

    var isEncrypt: Boolean = false,

    var password: String? = null
)

fun NoteEntity.toNote() = Note(
    id = this.id,
    title = this.title,
    description = this.description,
    createdAt = this.createdAt,
    modifiedAt = this.modifiedAt,
    isEncrypt = this.isEncrypt,
    password = this.password
)

fun Note.toNoteEntity() = NoteEntity(
    id = this.id,
    title = this.title,
    description = this.description,
    createdAt = this.createdAt,
    modifiedAt = this.modifiedAt,
    isEncrypt = this.isEncrypt,
    password = this.password
)
