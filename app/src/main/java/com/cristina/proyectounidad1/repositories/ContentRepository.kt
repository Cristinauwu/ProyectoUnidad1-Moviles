package com.cristina.proyectounidad1.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.cristina.proyectounidad1.models.entities.Content
import com.cristina.proyectounidad1.models.roomdb.ContentDB

class ContentRepository(context: Context) {

    private val contactDB = ContentDB.getInstance(context)
    private val contactDAO = contactDB.contentDAO()

    suspend fun insertContent(content: Content) {
        contactDAO.insertContent(content)
    }

    suspend fun getAllContents(): List<Content> {
        return contactDAO.getAllContentSync()
    }

    fun getAllContentLiveData(): LiveData<List<Content>> {
        return contactDAO.getAllContent()
    }
}