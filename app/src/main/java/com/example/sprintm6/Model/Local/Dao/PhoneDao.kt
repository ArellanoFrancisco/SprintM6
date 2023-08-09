package com.example.sprintm6.Model.Local.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sprintm6.Model.Local.Entities.PhoneDetails
import com.example.sprintm6.Model.Local.Entities.PhoneList

@Dao
interface PhoneDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPhones(phoneList: List<PhoneList>)

    @Query("SELECT * FROM List_Table ORDER BY id ASC")
    fun getAllPhone(): LiveData<List<PhoneList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoneDetail(phoneDetails: PhoneDetails)

    @Query("SELECT * FROM Phone_Table WHERE id = :id")
    fun getPhoneDetailById(id: Int): LiveData<PhoneDetails>
}

