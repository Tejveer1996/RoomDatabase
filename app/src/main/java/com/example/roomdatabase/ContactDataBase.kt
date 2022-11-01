package com.example.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

//@Database(entities = [Contact::class], version = 1)  // Linking entity(Contact) to DataBase
@Database(entities = [Contact::class], version = 2)  // Defining the above line with version 2
@TypeConverters(Converters::class)   // passing TypeConverters class to DataBase
abstract class ContactDataBase : RoomDatabase() {
    abstract fun contactDAO(): ContactDAO  // Linking DAO to DataBase

    // make database Instance singleton
    companion object {
           // Migration object
        val migration_1_2 = object :Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                // Query for adding column in Table
                database.execSQL("ALTER TABLE Contact ADD Column isActive INTEGER NOT NULL DEFAULT(1)")
            }

        }

        @Volatile   // it will tell all the thread in the application about the assigning of value to Instance of DataBase
        private var Instance: ContactDataBase? = null
        fun getDatabase(context: Context): ContactDataBase {
            if (Instance == null) {       // Instance of DataBase
                synchronized(this){   // create a lock on the dataBase Instance
                    Instance = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDataBase::class.java,
                        "contactDB"
                    ).addMigrations(migration_1_2)
                        .build()
                }

            }
            return Instance!!
        }
    }

}