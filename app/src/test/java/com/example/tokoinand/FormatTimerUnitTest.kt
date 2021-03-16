package com.example.tokoinand

import com.example.tokoinand.ui.binding.formatTime
import org.junit.Assert
import org.junit.Test

class FormatTimerUnitTest {
    @Test
    fun format_time_input_correct() {
        val timerUTC = "2021-03-15T04:08:47Z"
        val result = formatTime(timerUTC)
        val expect = "04:08:47 2021-03-15"
        Assert.assertEquals(expect, result)
    }
    @Test
    fun format_time_input_empty() {
        val timerUTC = ""
        val result = formatTime(timerUTC)
        val expect = ""
        Assert.assertEquals(expect, result)
    }
    @Test
    fun format_time_input_incorrect_format() {
        val timerUTC = "2021-03-15T04:08:47.3Z"
        val result = formatTime(timerUTC)
        val expect = ""
        Assert.assertEquals(expect, result)
    }
    @Test
    fun format_time_input_null() {
        val timerUTC = null
        val result = formatTime(timerUTC)
        val expect = ""
        Assert.assertEquals(expect, result)
    }
}