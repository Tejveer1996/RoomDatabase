package com.example.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDAO {
    // here we implement those methods(CRUD) that are defined in Room itself
    @Insert
  suspend  fun insertContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
  suspend  fun deleteContact(contact: Contact)

    @Query("SELECT*FROM Contact")   // for Selection and Read Operation
    fun getContact(): LiveData<List<Contact>>    // Room is compatible with LiveData
}