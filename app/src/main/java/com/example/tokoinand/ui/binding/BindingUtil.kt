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
fun formatTime(timer: String?) : String{
    if (timer == null)
        return ""
    return try {
        val utcFormat =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",Locale.ROOT)
        utcFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = utcFormat.parse(timer)
        SimpleDateFormat("HH:mm:ss yyyy-MM-dd", Locale.ROOT).format(date)
    }catch (exception : Exception){
        ""
    }

}