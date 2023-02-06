package com.dogandpigs.fitnote.presentation.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

internal fun Int.format(): String = this.toString().format()
internal fun Double.format(): String = this.toString().format()

internal fun String.format(originalText: String = "0"): String {
    return if (this.isEmpty()) {
        "0"
    } else {
        this.toIntOrNull()?.toString()
            ?: this.toDoubleOrNull()?.let {
//                if (it % 1 == 0.0) {
//                    it.roundToInt()
//                } else {
                ((it * 100).roundToInt() / 100)
//                }
                    .toString()
            } ?: originalText
    }
}

internal fun Date.format(): String =
    SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(this)

internal fun Date.formatYYYYMMDD(): String =
    SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(this)


internal fun formatGender(gender: Int?): String = when (gender) {
    1 -> "남성"
    2 -> "여성"
    else -> "오류"
}

internal fun String.formatDate(): String? =
    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.KOREA).parse(this)?.format()
internal fun trimTrailingZero(value: String?): String? {
    return if (!value.isNullOrEmpty()) {
        if (value.indexOf(".") < 0) {
            value
        } else {
            value.replace("0*$".toRegex(), "").replace("\\.$".toRegex(), "")
        }
    } else {
        value
    }
}
