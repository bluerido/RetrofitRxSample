package test.rido.com.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import test.rido.com.myapplication.R
import test.rido.com.myapplication.view.adapter.SimplePagerAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        val adapter =
            SimplePagerAdapter(supportFragmentManager)
        viewpager.adapter = adapter

        tabs.setupWithViewPager(viewpager)
    }
}
