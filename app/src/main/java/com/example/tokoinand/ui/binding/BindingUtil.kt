package com.example.tokoinand.ui.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("formatUTCTimer")
fun TextView.formatUTCTimer(utcTime : String?){
    text = formatTime(utcTime)
}
// convert UTC time to local time
fun formatTime(timer: String?) : String{
    return try {
        val utcFormat =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",Locale.ROOT)
        val date = utcFormat.parse(timer)
        SimpleDateFormat("HH:mm:ss yyyy-MM-dd", Locale.US).format(date)
    }catch (exception : Exception){
        ""
    }

}