package com.dogandpigs.fitnote.data.util

import com.dogandpigs.fitnote.data.source.remote.model.ResBase
import retrofit2.Response

internal fun <T> handleResponseResBase(
    response: Response<ResBase<T>>,
    isCheck: Boolean = true,
): T? {
    if (!response.isSuccessful) {
        error("response is not successful")
    } else if (response.body() == null) {
        error("response body is null")
    } else {
        val statusCode = response.body()?.statusCode?.toIntOrNull()

        if (isCheck) {
            val result = response.body()?.result?.toBoolean() ?: false
            check(result) { "result is false" }

            val message = response.body()?.message
            checkNotNull(statusCode) { "statusCode is null" }
            check(statusCode <= 200) { message.toString() }
        }


        return response.body()?.data
    }
}

internal fun <T> handleResponse(
    response: Response<ResBase<T>>,
    isCheck: Boolean = true,
): ResBase<T>? {
    if (!response.isSuccessful) {
        error("response is not successful")
    } else if (response.body() == null) {
        error("response body is null")
    } else {
        val statusCode = response.body()?.statusCode?.toIntOrNull()

        if (isCheck) {
            val result = response.body()?.result?.toBoolean() ?: false
            check(result) { "result is false" }

            val message = response.body()?.message
            checkNotNull(statusCode) { "statusCode is null" }
            check(statusCode <= 200) { message.toString() }
        }


        return response.body()
    }
}
