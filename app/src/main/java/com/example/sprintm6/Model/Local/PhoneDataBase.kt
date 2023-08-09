package com.example.sprintm6.Model.Local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sprintm6.Model.Local.Dao.PhoneDao
import com.example.sprintm6.Model.Local.Entities.PhoneDetails
import com.example.sprintm6.Model.Local.Entities.PhoneList


@Database(entities = [PhoneDetails:: class,PhoneList::class], version = 1, exportSchema = false)
abstract class PhoneDataBase : RoomDatabase() {
    abstract fun getPhoneDao(): PhoneDao

    companion object {


        @Volatile
        private var INSTANCE: PhoneDataBase? = null

        fun getdatabase(context: Context): PhoneDataBase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhoneDataBase::class.java,
                    "phone_database"
                ).build()
                INSTANCE = instance

                return instance
            }
        }
    }
}