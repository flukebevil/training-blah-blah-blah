package com.example.fluke.training.ui.main.option

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fluke.training.R
import com.example.fluke.training.ui.later.ViewLaterActivity
import com.example.fluke.training.ui.myfav.ViewMyFavActivity
import kotlinx.android.synthetic.main.fragment_option.*

class OptionFragment : android.support.v4.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater.inflate(R.layout.fragment_option, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myFavFrame.setOnClickListener { context?.startActivity(Intent(context , ViewMyFavActivity::class.java)) }
        laterFrame.setOnClickListener { startActivity(Intent(context , ViewLaterActivity::class.java)) }
    }
}