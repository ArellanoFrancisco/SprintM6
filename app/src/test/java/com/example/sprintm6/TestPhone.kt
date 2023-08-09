package com.example.sprintm6

import com.example.sprintm6.Model.Local.Entities.PhoneList
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TestPhone {
    private lateinit var phoneList: PhoneList


    @Before
    fun setup(){
        phoneList = PhoneList(
        id = 1,
        name = "PRUEBA",
        price = 3000,
        image = "Imagen de telefono"
        )
    }
@Test

fun testPhoneConstructor(){
    assert(phoneList.id==1)
    assert(phoneList.name=="PRUEBA")
    assert(phoneList.price== 3000)
    assert(phoneList.image=="Imagen de telefono")
}



}