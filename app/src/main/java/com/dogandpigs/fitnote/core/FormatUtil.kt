package com.dogandpigs.fitnote.core

import java.sql.Timestamp
import java.text.SimpleDateFormat

object FormatUtil {
    fun millToDate(millis: Long): String {
        val pattern = "yyyy-MM-dd"
        return SimpleDateFormat(pattern).format(Timestamp(millis))
    }
}