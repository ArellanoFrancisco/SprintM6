package com.example.sprintm6

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sprintm6.View.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailInstumentalActivityTest {

    @Test
    fun testMostrarValidatePhone(){

        val context=androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(context,MainActivity::class.java)

        intent.putExtra("id", 1)
        intent.putExtra("name", "prueba")
        intent.putExtra("price", 3000)
        intent.putExtra("image", "image")

        val activityEscenary = ActivityScenario.launch<MainActivity>(intent)

        activityEscenary.onActivity { activity ->

            assertNotNull(activity)

            assertEquals(1, activity.intent.getIntExtra("id", 1))
            assertEquals("prueba", activity.intent.getStringExtra("name",))
            assertEquals(3000, activity.intent.getIntExtra("price", 2990))
            assertEquals("image", activity.intent.getStringExtra("image",))
        }
    }
}