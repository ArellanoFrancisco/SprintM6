package com.example.sprintm6.Model.Remote

import android.provider.ContactsContract.CommonDataKinds.Phone
import com.example.sprintm6.Model.Local.Entities.PhoneDetails
import com.example.sprintm6.Model.Local.Entities.PhoneList
import com.example.sprintm6.Model.Remote.FromInternet.Details
import com.example.sprintm6.Model.Remote.FromInternet.ListPhone


fun fromInternetPhones( phoneList: List<ListPhone>) :List<PhoneList>{

    return phoneList.map {
        PhoneList(
            id= it.id,
            name = it.name,
            price = it.price,
            image = it.image
        )
    }
}


fun fromInternetPhonesDetails( details: Details) :PhoneDetails{

    return PhoneDetails(
        id = details.id,
        name = details.name,
        price = details.price,
        image = details.image,
        description = details.description,
        lastPrice = details.lastPrice,
        credit = details.credit
    )
}