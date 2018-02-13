package com.example.fluke.training.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.example.fluke.training.App
import com.example.fluke.training.di.ApplicationComponent
import com.google.gson.Gson
import javax.inject.Inject

@SuppressLint("Registered")
abstract class BaseActivity<V : BaseContractor.View, P : BaseContractor.Presenter<V>> :
        BaseContractor.View, AppCompatActivity() {
    @Inject
    protected lateinit var presenter: P

//    private val saveFav : SharedPreferences = applicationContext.getSharedPreferences("myfav", Context.MODE_PRIVATE )
//    private val editFav : SharedPreferences.Editor =saveFav.edit()
//    private val gson : Gson  = Gson()

    @LayoutRes
    protected abstract fun layoutContentView(): Int

    protected abstract fun doInjection(appComponent: ApplicationComponent)

    protected abstract fun setupView()

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doInjection(App.appComponent)
        setContentView(layoutContentView())
        presenter.attachView(this as V)
        setupView()
    }
}