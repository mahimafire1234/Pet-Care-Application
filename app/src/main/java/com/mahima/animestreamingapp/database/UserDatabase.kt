package com.mahima.animestreamingapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mahima.animestreamingapp.dao.userDAO
import com.mahima.animestreamingapp.entity.adminEntity

@Database(
    entities = (arrayOf(adminEntity::class)),
    version = 1,
    exportSchema = false
)
abstract class UserDatabase: RoomDatabase() {
//    create dao function
    abstract fun userDao(): userDAO
//    create a companion object

    companion object {
        //        instance
        private var instance: UserDatabase? = null
//        function

        fun getDatabase(context: Context): UserDatabase {
            if (instance == null) {
                synchronized(UserDatabase::class) {
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }
//        function builddatabase
        private fun buildDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                UserDatabase ::class.java,
                "UserDatabase"
            ).build()
    }
}