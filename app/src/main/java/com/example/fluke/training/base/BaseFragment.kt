package com.example.fluke.training.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fluke.training.App
import com.example.fluke.training.di.ApplicationComponent
import javax.inject.Inject

abstract class BaseFragment<V : BaseContractor.View, P : BaseContractor.Presenter<V>> :
        BaseContractor.View, Fragment() {
    @Inject
    protected lateinit var presenter: P

    @LayoutRes
    protected abstract fun layoutInflate(): Int
    protected abstract fun doInjection(appComponent: ApplicationComponent)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater.inflate(layoutInflate(), container, false)

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doInjection(App.appComponent)
        presenter.attachView(this as V)
        initFunction()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    protected abstract fun setAdapter()

    protected abstract fun initFunction()


}