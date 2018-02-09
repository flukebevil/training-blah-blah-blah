package com.example.fluke.training.theme

import com.example.fluke.training.base.BaseContractor
import com.example.fluke.training.model.Movie
import java.lang.ref.WeakReference

class ThemeContractor {

    interface Presenter : BaseContractor.Presenter<View>{

    }

    interface View: BaseContractor.View {

    }
}