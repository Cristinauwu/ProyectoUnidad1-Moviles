package com.cristina.proyectounidad1.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.cristina.proyectounidad1.models.entities.Content
import com.cristina.proyectounidad1.models.roomdb.ContentDB
import com.cristina.proyectounidad1.repositories.ContentRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
   private val ContRepository = ContentRepository(application)

    fun getContents() : LiveData<List<Content>> = liveData {
        val contents = ContRepository.getAllContents()
        emit(contents)
    }
}