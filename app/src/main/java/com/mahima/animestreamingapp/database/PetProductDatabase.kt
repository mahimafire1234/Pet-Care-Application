package com.mahima.animestreamingapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mahima.animestreamingapp.dao.PetProductDAO
import com.mahima.animestreamingapp.entity.PetProductEntity

@Database(
    entities = (arrayOf(PetProductEntity::class)),
    version = 1,
    exportSchema = false
)
abstract class PetProductDatabase: RoomDatabase() {

//    create and instance of dao
    abstract fun petProductDao() : PetProductDAO

//    companion object
    companion object{
        private var instance: PetProductDatabase? = null

         fun getDatabase(context: Context): PetProductDatabase {
            if (instance == null){
                synchronized(PetProductDatabase::class){
                    instance=buildDatabase(context)
                }
            }
            return instance!!
        }

        private fun buildDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                PetProductDatabase::class.java,
                "PetDatabase"
            ).build()
        }
}