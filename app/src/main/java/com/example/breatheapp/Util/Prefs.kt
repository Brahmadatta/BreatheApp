package com.example.breatheapp.Util

import android.app.Activity
import android.content.SharedPreferences
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi

class Prefs(activity: Activity) {

//    private lateinit var preferences: SharedPreferences

    private val preferences : SharedPreferences = activity.getPreferences(Activity.MODE_PRIVATE)

    fun setDate(milliseconds : Long)
    {
        preferences.edit().putLong("seconds",milliseconds).apply()
    }

    val date : String @RequiresApi(Build.VERSION_CODES.N)
    get() {
        val milliDate = preferences.getLong("seconds",0)

        val amOrPm : String

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliDate

        val a = calendar.get(Calendar.AM_PM)

        if (a == Calendar.AM)
        {
            amOrPm = "AM"
        }else{
            amOrPm = "PM"
        }

        return ("Last " + calendar.get(Calendar.HOUR_OF_DAY) + ": "
                + calendar.get(Calendar.MINUTE) + " " + amOrPm)

    }

    var sessions : Int
    get() = preferences.getInt("sessions",0)
    set(session) = preferences.edit().putInt("sessions",session).apply()

    var breaths : Int
    get() = preferences.getInt("breaths",0)
    set(breath) = preferences.edit().putInt("breaths",breath).apply()

//    @RequiresApi(Build.VERSION_CODES.N)
//    private fun getDate() : String {
//        val milliDate = preferences.getLong("seconds",0)
//        var amOrPm : String
//
//        val calendar = Calendar.getInstance()
//        calendar.timeInMillis = milliDate
//
//        val a = calendar.get(Calendar.AM_PM)
//
//        if (a == Calendar.AM)
//        {
//            amOrPm = "AM"
//        }else{
//            amOrPm = "PM"
//        }
//
//        val time = "Last ${calendar.get(Calendar.HOUR_OF_DAY)} :" +
//                "${calendar.get(Calendar.MINUTE)}" +
//                "$amOrPm" //11:45 AM/PM
//
//        return time
//    }


    //In Kotlin Way


}