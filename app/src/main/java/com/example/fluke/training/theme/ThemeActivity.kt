package com.example.fluke.training.theme

import android.view.Menu
import android.view.MenuItem
import com.example.fluke.training.R
import com.example.fluke.training.base.BaseActivity
import com.example.fluke.training.ui.main.adapter.Pager
import kotlinx.android.synthetic.main.activity_home.*

class ThemeActivity : BaseActivity() {

    override fun layoutContentView(): Int = 0 //todo replace some layout
    private var pager: Pager? = null

    override fun setupView() {
        pager = Pager(supportFragmentManager)
        container.adapter = pager
        tabLayout?.setupWithViewPager(container)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}