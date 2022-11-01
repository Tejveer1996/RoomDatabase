package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var dataBase: ContactDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Instance of singleton DataBase
        dataBase = ContactDataBase.getDatabase(this)

      //  dataBase = Room.databaseBuilder(applicationContext, ContactDataBase::class.java, "contactDB")
           // .build()  // DataBase Object, Not singleton

        // Insertion in DataBase
        GlobalScope.launch(Dispatchers.IO) {
            dataBase.contactDAO().insertContact(Contact(0, "John", "999",Date(23032023),0))


        }

    }

    fun getData(view: View) {
        dataBase.contactDAO().getContact().observe(this, Observer {
            Log.d("Contact", it.toString())
        })
    }
}