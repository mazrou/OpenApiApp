package com.e.jetpackcours.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.e.jetpackcours.data.model.AccountProperties
import com.e.jetpackcours.data.model.AuthToken


@Database(entities = [AuthToken::class ,
                      AccountProperties::class],
          version = 1
)
abstract class AppDatabase  : RoomDatabase(){

    abstract fun getAuthTokenDao() : AuthTokenDao

    abstract fun getAccountPropertiesDao() : AccountPropertiesDao


    companion object{
        const val DATABASE_NAME = "app_db"


        @Volatile private  var instance  : AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }
        private fun buildDatabase(context: Context)  =
            Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java , DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

    }

}