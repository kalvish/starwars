package com.kalan.starwarsnotebook.code.presentation.util

import android.content.Context
import com.kalan.starwarsnotebook.R
import com.kalan.starwarsnotebook.core.domain.util.NetworkError

fun NetworkError.toString(context: Context): String {
    val resId = when(this) {
        NetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeout
        NetworkError.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
        NetworkError.NO_INTERNET -> R.string.error_no_internet
        NetworkError.SERVER_ERROR -> R.string.error_something_went_wrong
        NetworkError.SERIALIZATION -> R.string.error_something_went_wrong
        NetworkError.UNKNOWN -> R.string.error_something_went_wrong
    }
    return context.getString(resId)
}