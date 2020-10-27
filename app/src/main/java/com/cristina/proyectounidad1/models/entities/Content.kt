package com.cristina.proyectounidad1.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Content (
    @PrimaryKey(autoGenerate = true)
    val id: Long?=null,
    val name: String,
    val lastName: String,
    val email: String,
    val service: String,
    val option: String
)
{ override fun equals(other: Any?): Boolean {
    if (other is Content)
        return other.id==this.id
    return false
}}