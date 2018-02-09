package com.example.fluke.training.base

import io.reactivex.observers.DisposableObserver
import retrofit2.Response

class BaseSubScribe<T>(val callback: ResponseWtf<T>): DisposableObserver<Response<T>>() {
    override fun onComplete() {
        //TODO i dont need todo this task
    }

    override fun onNext(t: Response<T>) {
        t.body()?.let { callback.success(it) }
    }

    override fun onError(e: Throwable) {
        //todo return some error shower maybe toast
    }

    interface ResponseWtf<in T>{
        fun success(t : T)
    }

}