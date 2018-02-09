package com.example.fluke.training.theme

import com.example.fluke.training.base.BasePresenter
import com.example.fluke.training.base.BaseSubScribe
import com.example.fluke.training.model.GetData
import com.example.fluke.training.model.TelevisionList
import javax.inject.Inject

class ThemePresenter @Inject constructor(private val getData: GetData) :
        BasePresenter<ThemeContractor.View>(),
        BaseSubScribe.ResponseWtf<TelevisionList>,
        ThemeContractor.Presenter {
    override fun success(t: TelevisionList) {
    }
}