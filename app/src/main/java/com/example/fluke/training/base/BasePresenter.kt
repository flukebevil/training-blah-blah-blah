package com.example.fluke.training.base

import java.lang.ref.WeakReference

abstract class BasePresenter<V : BaseContractor.View> :
        BaseContractor.Presenter<V> {

    private var wtfView: WeakReference<V>? = null
    override fun attachView(view: V) {
        this.wtfView = WeakReference(view)
    }

    override fun getView(): V? = wtfView?.get()
}