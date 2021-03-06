package com.cristina.proyectounidad1.models.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cristina.proyectounidad1.models.entities.Content

@Dao
abstract class ContentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertContent(content: Content)

    @Query("SELECT * FROM Content")
    abstract fun getAllContent() : LiveData<List<Content>>

    @Query("SELECT * FROM Content")
    abstract suspend fun getAllContentSync() : List<Content>
}