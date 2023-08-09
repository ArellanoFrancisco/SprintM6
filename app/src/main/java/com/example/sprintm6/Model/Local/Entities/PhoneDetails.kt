package com.example.sprintm6.Model.Local.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Phone_Table")
data class PhoneDetails (
    @PrimaryKey
    val id: Int,
    val name:String,
    val price:Int,
    val image:String,
    val description:String,
    val lastPrice:Int,
    val credit:Boolean
)
