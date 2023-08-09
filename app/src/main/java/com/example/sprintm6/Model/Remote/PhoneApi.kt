package com.example.sprintm6.Model.Remote

import com.example.sprintm6.Model.Remote.FromInternet.Details
import com.example.sprintm6.Model.Remote.FromInternet.ListPhone
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface PhoneApi {


    @GET("products")
    suspend fun fecthPhoneList(): Response<List<ListPhone>>


    // seleccionar uno

    @GET("details/{id}")
    suspend fun fechPhoneDetail(@Path("id") id: Int): Response<Details>
}