package me.gilo.core.entity

import java.util.*

data class Note(
    var id: Int = 0,
    var title: String? = null,
    var description: String? = null,
    var createdAt: Date? = null,
    var modifiedAt: Date? = null,
    var isEncrypt: Boolean = false,
    var password: String? = null
)