package com.example.android_fridge.data

import com.example.android_fridge.data.Result.Status.ERROR
import com.example.android_fridge.data.Result.Status.SUCCESS

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

fun <T, A> resultLiveData(databaseQuery: () -> LiveData<T>,
                          networkCall: suspend () -> Result<A>,
                          saveCallResult: suspend (A) -> Unit): LiveData<Result<T>> =
    liveData(Dispatchers.IO) {
        emit(Result.loading<T>())
        val source = databaseQuery.invoke().map { Result.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == SUCCESS) {
            saveCallResult(responseStatus.data!!)
        } else if (responseStatus.status == ERROR) {
            emit(Result.error<T>(responseStatus.message!!))
            emitSource(source)
        }
    }