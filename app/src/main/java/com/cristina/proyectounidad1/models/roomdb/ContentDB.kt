package com.cristina.proyectounidad1.models.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cristina.proyectounidad1.models.entities.Content
import com.cristina.proyectounidad1.models.dao.ContentDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Content::class],
    version = 2,
    exportSchema = true
)
abstract class ContentDB : RoomDatabase() {
    abstract fun contentDAO(): ContentDAO

    companion object{
        @JvmStatic
        @Volatile
        private var instance: ContentDB? = null

        @Synchronized
        fun getInstance(context: Context): ContentDB{
            if(instance == null){
                instance = Room.databaseBuilder(
                    context,
                    ContentDB::class.java,
                    "information.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                CoroutineScope(Dispatchers.IO).launch {
                    instance?.initDB()
                }

            }
            return  instance as ContentDB
        }
    }

    suspend fun initDB(){
        val contentDAO = contentDAO()
        if (contentDAO.getAllContentSync().isEmpty()){
            contentDAO.insertContent(
                Content(
                    name = "Cristina",
                    lastName = "Beltran",
                    email = "cris@hotmail.com",
                    service = "Apartar",
                    option = "Rosa"
                )
            )

            contentDAO.insertContent(
                Content(
                    name = "Magdalena",
                    lastName = "Beltran",
                    email = "magda@hotmail.com",
                    service = "Comprar",
                    option = "Girasol"
                )
            )
        }
    }
}

/*@Volatile
private var instance: ContentDB? = null*/