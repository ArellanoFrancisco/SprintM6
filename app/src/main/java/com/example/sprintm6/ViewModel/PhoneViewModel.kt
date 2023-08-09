package com.example.sprintm6.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sprintm6.Model.Local.Dao.PhoneDao
import com.example.sprintm6.Model.Local.Entities.PhoneDetails
import com.example.sprintm6.Model.Local.Entities.PhoneList
import com.example.sprintm6.Model.Local.PhoneDataBase
import com.example.sprintm6.Model.PhoneRepository
import kotlinx.coroutines.launch

class PhoneViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : PhoneRepository

    private val phoneDetailsLiveData = MutableLiveData<PhoneDetails>()

    private val idSelected = -1

    init{
        val bd= PhoneDataBase.getdatabase(application)
        val phoneDao= bd.getPhoneDao()
        repository = PhoneRepository(phoneDao)
        viewModelScope.launch {
            repository.fetchPhone()
        }
    }

    fun getPhoneList(): LiveData<List<PhoneList>> = repository.PhoneListLiveData

    fun getPhoneDetail(): LiveData<PhoneDetails> = phoneDetailsLiveData

    fun getPhoneDetailsByIdFromInternet(id: Int)= viewModelScope.launch {


        val phoneDetail = repository.fetchPhoneDetail(id)
        phoneDetail?.let {

            phoneDetailsLiveData.postValue(it)
        }

    }

}