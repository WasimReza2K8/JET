package com.example.core.ext

import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

fun Throwable.isNetworkException() =
    this is IOException || this is UnknownHostException || this is HttpException
