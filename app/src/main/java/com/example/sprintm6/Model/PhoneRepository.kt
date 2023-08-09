package com.example.sprintm6.Model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.sprintm6.Model.Local.Dao.PhoneDao
import com.example.sprintm6.Model.Local.Entities.PhoneDetails
import com.example.sprintm6.Model.Remote.RetrofitPhone
import com.example.sprintm6.Model.Remote.fromInternetPhones
import com.example.sprintm6.Model.Remote.fromInternetPhonesDetails
import retrofit2.Retrofit

class PhoneRepository (private val phoneDao : PhoneDao) {

    private val networkService = RetrofitPhone.retrofitInstance()

    val PhoneListLiveData = phoneDao.getAllPhone()

    val PhoneDetailsLiveData = MutableLiveData<PhoneDetails>()



    suspend fun fetchPhone(){
        val service = kotlin.runCatching { networkService.fecthPhoneList() }

        service.onSuccess {
            when (it.code()){
                in 200..299 ->it.body()?.let {

                    Log.d("Phones", it.toString())

                    phoneDao.insertAllPhones(fromInternetPhones(it))

                }
                else-> Log.d("Repo","${it.code()}-${it.errorBody()}")
            }
            service.onFailure {
                Log.e("Error", "${it.message}")
            }

        }

    }


    suspend fun fetchPhoneDetail(id: Int): PhoneDetails?{

        val service = kotlin.runCatching { networkService.fechPhoneDetail(id) }

        return service.getOrNull()?.body()?.let { Details ->

            val phoneDetails = fromInternetPhonesDetails(Details)

            phoneDao.insertPhoneDetail(phoneDetails)
            phoneDetails
        }
    }








}