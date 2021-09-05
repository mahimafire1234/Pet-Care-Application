package com.mahima.animestreamingapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mahima.animestreamingapp.dao.PetCareTakerDAO
import com.mahima.animestreamingapp.entity.PetCareTakerEntity


@Database(
    entities = (arrayOf(PetCareTakerEntity::class)),
    version = 2,
    exportSchema = false
)
abstract class PetCareDatabase: RoomDatabase() {

    //    create and instance of dao
    abstract fun petCareDao() : PetCareTakerDAO

    //    companion object
    companion object{
        private var instance: PetCareDatabase? = null

        fun getDatabase(context: Context): PetCareDatabase {
            if (instance == null){
                synchronized(PetCareDatabase::class){
                    instance=buildDatabase(context)
                }
            }
            return instance!!
        }

        private fun buildDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                PetCareDatabase::class.java,
                "PetCareDatabase_"
            ).fallbackToDestructiveMigration()
                .build()
    }
}