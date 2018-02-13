package com.example.fluke.training.ui.myfav

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.util.Log
import com.example.fluke.training.R
import com.example.fluke.training.model.Movie
import com.example.fluke.training.model.MovieList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_view_my_fav.*

@Suppress("UNUSED_EXPRESSION")
class ViewMyFavActivity : AppCompatActivity() {
    private val myfavAdapter : MyFavAdapter by lazy { MyFavAdapter(arrayListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_my_fav)
        favList.apply {
            layoutManager = LinearLayoutManager(context , OrientationHelper.HORIZONTAL , false)
            adapter = myfavAdapter
        }
        showFavList()
    }

    private fun showFavList(){
        val gson = Gson()
        val show : SharedPreferences = applicationContext.getSharedPreferences("myfav",Context.MODE_PRIVATE)
        val json : String = show.getString("movie","")
        val data : MovieList = gson.fromJson(json , MovieList::class.java)
        Log.e("json myfav" , json)
        data.results.let { it -> it?.let { it1 -> myfavAdapter.setItem(it1) } }
    }
}
