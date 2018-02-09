package com.example.fluke.training.model

import com.example.fluke.training.base.BaseService
import com.example.fluke.training.base.BaseSubScribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class GetData(private val baseApi: BaseService) {

    interface TelevisionTopRateListener {
        fun onTopRateSuccess(t: TelevisionList)
        fun upComingSuccess(t: TelevisionList)
    }

    interface MovieTopRateListener {
        fun onTopRateSuccess(t: MovieList)
        fun upComingSuccess(t: MovieList)
    }

    fun selectMoviePopData() = baseApi.selectMoviePopular()
    fun selectMovieTopData() = baseApi.selectMovieTopRate()
    fun selectMovieUpComing() = baseApi.selectMovieUpcoming()
    fun selectTelevisionPopData() = baseApi.televisionPopular()
    fun selectTelevisionTopRate() = baseApi.televisionTopRate()
    fun selectTelevisionUpComing() =  baseApi.televisionOnTheAir()

    fun requestMoviePopData(callback: BaseSubScribe.ResponseWtf<MovieList>) {
        selectMoviePopData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(callback))
    }

    fun requestTelevisionPopData(callback: BaseSubScribe.ResponseWtf<TelevisionList>) {
        selectTelevisionPopData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(callback))
    }

    fun requestTelevisionTopRate(callback: TelevisionTopRateListener) {
        selectTelevisionTopRate().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(object : BaseSubScribe.ResponseWtf<TelevisionList> {
                    override fun success(t: TelevisionList) {
                        callback.onTopRateSuccess(t)
                    }
                }))
    }

    fun requestTelevisionUpComing(callback: TelevisionTopRateListener) {
        selectTelevisionUpComing().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(object : BaseSubScribe.ResponseWtf<TelevisionList> {
                    override fun success(t: TelevisionList) {
                        callback.upComingSuccess(t)
                    }
                }))
    }

    fun requestMovieTopRate(callback: MovieTopRateListener) {
        selectMovieTopData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(object : BaseSubScribe.ResponseWtf<MovieList> {
                    override fun success(t: MovieList) {
                        callback.onTopRateSuccess(t)
                    }

                }))
    }

    fun requestMovieUpComing(callback: MovieTopRateListener) {
        selectMovieUpComing().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(object : BaseSubScribe.ResponseWtf<MovieList> {
                    override fun success(t: MovieList) {
                        callback.upComingSuccess(t)
                    }

                }))
    }
}