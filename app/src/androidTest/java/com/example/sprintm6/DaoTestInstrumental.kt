package com.example.sprintm6

import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sprintm6.Model.Local.Dao.PhoneDao
import com.example.sprintm6.Model.Local.PhoneDataBase
import com.example.sprintm6.Model.Local.Entities.PhoneDetails
import com.example.sprintm6.Model.Local.Entities.PhoneList
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runner.manipulation.Ordering.Context



@RunWith(AndroidJUnit4::class)
class DaoTestInstrumental {

    private lateinit var phoneDao: PhoneDao
    private lateinit var phoneDataBase: PhoneDataBase


    @Before
    fun setup(){
        val context=ApplicationProvider.getApplicationContext<android.content.Context>()
        phoneDataBase=  Room.inMemoryDatabaseBuilder(context,PhoneDataBase::class.java).build()
        phoneDao=phoneDataBase.getPhoneDao()
    }

    @After
    fun shutdown(){
        phoneDataBase.close()
    }

    @Test
    fun testInsertListPhone() = runBlocking  {
        val phoneList= listOf(
            PhoneList(1, "prueba",3000,"image"),
            PhoneList(2, "prueba2",4000,"image2")
        )
        phoneDao.insertAllPhones(phoneList)

        val phoneLiveData = phoneDao.getAllPhone()
        val listPhone : List<PhoneList> = phoneLiveData.value?: emptyList()

        assertThat(listPhone, not(emptyList()))
        assertThat(listPhone.size, equalTo(2))

    }

    @Test
     fun testInsertDetailsPhone()= runBlocking{
        val details = PhoneDetails(5, "prueba", 2000,
            "image3", "description", 1000, false
        )
        phoneDao.insertPhoneDetail(details)
        val phoneLiveData = phoneDao.getPhoneDetailById(5)
        val phoneValue= phoneLiveData.value

        assertThat(phoneValue?.id, equalTo(5))
        assertThat(phoneValue?.credit, equalTo(false))

    }
}