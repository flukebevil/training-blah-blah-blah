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

    interface DiscoverListener {
        fun onCallGenresMovieSuccess(t: MovieTypeList)
        fun onCallGenresTelevisionSuccess(t: TelevisionTypeList)
    }

    interface ResultListener {
        fun onMovieGenresCallDetail(t: MovieList)
        fun onTelevisionGenresCallDetail(t: TelevisionList)
    }

    interface DetailListener {
        fun onCrewCallback(t: CreditList)
        fun onCallVideoPathSuccess(t: MovieVideoPathList)
    }

    private fun selectMovieGenresItem() = baseApi.selectMovieGenres()
    private fun selectMovieGenresResultList(key: String) = baseApi.selectMovieByGenres(key)
    private fun selectMoviePopData() = baseApi.selectMoviePopular()
    private fun selectMovieTopData() = baseApi.selectMovieTopRate()
    private fun selectMovieUpComing() = baseApi.selectMovieUpcoming()
    private fun selectMovieFromKey(key: String) = baseApi.search(key)
    private fun selectMovieCredit(key: String) = baseApi.selectMovieCredit(key)
    private fun selectTelevisionPopData() = baseApi.televisionPopular()
    private fun selectTelevisionTopRate() = baseApi.televisionTopRate()
    private fun selectTelevisionUpComing() = baseApi.televisionOnTheAir()
    private fun selectTelevisionGenresItem() = baseApi.selectTelevisionGenres()
    private fun selectTelevisionGenresResultList(key: String) = baseApi.selectTelevisionByGenres(key)
    private fun selectTelevisionCredit(key: String) = baseApi.selectTelevisionCredit(key)
    private fun selectMovieVideoPath(movieId: String) = baseApi.selectMovieVideoPath(movieId)
    private fun selectTvVideoPath(movieId: String) = baseApi.selectTelevisionVideoPath(movieId)

    fun requestMovieGenresResult(key: String, callback: ResultListener) {
        selectMovieGenresResultList(key).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(object : BaseSubScribe.ResponseWtf<MovieList> {
                    override fun success(t: MovieList) {
                        callback.onMovieGenresCallDetail(t)
                    }
                }))
    }

    fun requestMovieVideoPath(movieId: String, callback: DetailListener) {
        selectMovieVideoPath(movieId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(object : BaseSubScribe.ResponseWtf<MovieVideoPathList> {
                    override fun success(t: MovieVideoPathList) {
                        callback.onCallVideoPathSuccess(t)
                    }
                }))
    }

    fun requestTvVideoPath(movieId: String, callback: DetailListener) {
        selectTvVideoPath(movieId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(object : BaseSubScribe.ResponseWtf<MovieVideoPathList> {
                    override fun success(t: MovieVideoPathList) {
                        callback.onCallVideoPathSuccess(t)
                    }
                }))
    }

    fun requestTelevisionGenresResult(key: String, call: ResultListener) {
        selectTelevisionGenresResultList(key).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(object : BaseSubScribe.ResponseWtf<TelevisionList> {
                    override fun success(t: TelevisionList) {
                        call.onTelevisionGenresCallDetail(t)
                    }
                }))
    }

    fun requestMoviePopData(callback: BaseSubScribe.ResponseWtf<MovieList>) {
        selectMoviePopData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(callback))
    }

    fun requestMovieCredit(key: String, callback: BaseSubScribe.ResponseWtf<CreditList>) {
        selectMovieCredit(key).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(callback))
    }

    fun requestMovieCrew(key: String, callback: DetailListener) {
        selectMovieCredit(key).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(object : BaseSubScribe.ResponseWtf<CreditList> {
                    override fun success(t: CreditList) {
                        callback.onCrewCallback(t)
                    }
                }))
    }

    fun requestTelevisionCrew(key: String, callback: DetailListener) {
        selectTelevisionCredit(key).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(object : BaseSubScribe.ResponseWtf<CreditList> {
                    override fun success(t: CreditList) {
                        callback.onCrewCallback(t)
                    }
                }))
    }

    fun requestTelevisionCredit(key: String, callback: BaseSubScribe.ResponseWtf<CreditList>) {
        selectTelevisionCredit(key).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(callback))
    }

    fun requestMovieDataSearch(key: String, callback: BaseSubScribe.ResponseWtf<MovieList>) {
        selectMovieFromKey(key).subscribeOn(Schedulers.io())
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

    fun requestMovieGenres(callback: DiscoverListener) {
        selectMovieGenresItem().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(object : BaseSubScribe.ResponseWtf<MovieTypeList> {
                    override fun success(t: MovieTypeList) {
                        callback.onCallGenresMovieSuccess(t)
                    }
                }))
    }

    fun requestTelevisionGenres(callback: DiscoverListener) {
        selectTelevisionGenresItem().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubScribe(object : BaseSubScribe.ResponseWtf<TelevisionTypeList> {
                    override fun success(t: TelevisionTypeList) {
                        callback.onCallGenresTelevisionSuccess(t)
                    }
                }))
    }
}