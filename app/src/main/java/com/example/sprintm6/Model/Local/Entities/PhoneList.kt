package com.example.sprintm6.Model.Local.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "List_Table")
data class PhoneList(
    @PrimaryKey
    val id:Int,
    val name:String,
    val price:Int,
    val image:String
)
