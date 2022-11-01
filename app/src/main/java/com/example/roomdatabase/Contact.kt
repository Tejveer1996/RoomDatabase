package com.example.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Contact")  // Name of the table or entity is Contact
data class Contact(
    @PrimaryKey(autoGenerate = true)  // Making id as primary key and autogenerate means increase on every new entry automatically
    val id : Long,
    val name :String,
    val phone :String,
    val createdDate: Date,
    val isActive :Int

)
