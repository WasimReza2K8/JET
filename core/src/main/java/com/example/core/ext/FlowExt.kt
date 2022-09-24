package com.example.core.ext

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> Flow<T>.distinct(past: MutableSet<T>): Flow<T> = flow {
    collect {
        val isNew = past.add(it)
        if (isNew) emit(it)
    }
}
