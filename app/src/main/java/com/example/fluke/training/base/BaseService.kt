package com.example.fluke.training.base

import com.example.fluke.training.model.*
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BaseService {

    @GET("search/movie")
    fun search(@Query("query") query: String): Observable<Response<MovieList>>

    @GET("tv/on_the_air?language=en-US&page=1")
    fun televisionOnTheAir(): Observable<Response<TelevisionList>>

    @GET("tv/popular?language=en-US&page=1")
    fun televisionPopular(): Observable<Response<TelevisionList>>

    @GET("tv/top_rated?language=en-US&page=1")
    fun televisionTopRate(): Observable<Response<TelevisionList>>

    @GET("discover/tv?language=en-US&sort_by=vote_average.desc&page=1&timezone=America%2FNew_York&include_null_first_air_dates=false")
    fun selectTelevisionByGenres(@Query("with_genres") query: String): Observable<Response<TelevisionList>>

    @GET("movie/popular?language=en-US&page=1")
    fun selectMoviePopular(): Observable<Response<MovieList>>

    @GET("movie/top_rated?&language=en-US&page=1")
    fun selectMovieTopRate(): Observable<Response<MovieList>>

    @GET("movie/upcoming?language=en-US&page=1")
    fun selectMovieUpcoming(): Observable<Response<MovieList>>

    @GET("movie/{movie_id}/credits?api_key=c1618550083ac39008a92222d9c8a6a9")
    fun selectMovieCredit(@Path("movie_id") query: String): Observable<Response<CreditList>>

    @GET("tv/{movie_id}/credits?api_key=c1618550083ac39008a92222d9c8a6a9")
    fun selectTelevisionCredit(@Path("movie_id") query: String): Observable<Response<CreditList>>

    @GET("discover/movie?language=en-US&sort_by=vote_count.desc&include_adult=false&include_video=false&page=1")
    fun selectMovieByGenres(@Query("with_genres") query: String): Observable<Response<MovieList>>

    @GET("tv/{tv_id}?language=en-US")
    fun selectTvDetail(@Path("tv_id") query: String): Observable<Response<TelevisionDetail>>

    @GET("tv/{tv_id}/season/{tv_season}?language=en-US")
    fun selectTvSeason(@Path("tv_id") tvId: String, @Path("tv_season") tvSeason: String)
            : Observable<Response<TelevisionSeason>>

    @GET("genre/movie/list?language=en-US")
    fun selectMovieGenres(): Observable<Response<MovieTypeList>>

    @GET("genre/tv/list?language=en-US")
    fun selectTelevisionGenres(): Observable<Response<TelevisionTypeList>>

    @GET("movie/{movie_id}/videos?language=en-US")
    fun selectMovieVideoPath(@Path("movie_id") query: String): Observable<Response<MovieVideoPathList>>

    @GET("tv/{movie_id}/videos?language=en-US")
    fun selectTelevisionVideoPath(@Path("movie_id") query: String): Observable<Response<MovieVideoPathList>>
}