package com.example.fluke.training.result

import com.example.fluke.training.base.BaseContractor
import com.example.fluke.training.model.Movie
import java.lang.ref.WeakReference

class ResultContractor {

    interface Presenter : BaseContractor.Presenter<View>{
        fun getDataFromKeyWord(key:String)

    }

    interface View: BaseContractor.View {
        fun getViewDataKeyWord(arr : List<Movie>)
    }
}