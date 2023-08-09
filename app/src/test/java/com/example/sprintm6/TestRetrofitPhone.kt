package com.example.sprintm6


import com.example.sprintm6.Model.Remote.RetrofitPhone
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TestRetrofitPhone {

    private lateinit var mockWebServer : MockWebServer

    @Before
    fun setup(){
        mockWebServer= MockWebServer()

    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }

    @Test
    fun testRetrofit() {

        val expectedBaseUrl = mockWebServer.url("/").toString()

        val retrofit = Retrofit.Builder()
            .baseUrl(expectedBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        RetrofitPhone.retrofitInstance=retrofit
        val retrofitInstace = RetrofitPhone.retrofitInstance
        assertEquals(expectedBaseUrl, retrofitInstace.baseUrl().toString())

    }

}