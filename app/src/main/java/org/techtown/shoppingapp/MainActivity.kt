package org.techtown.shoppingapp

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.techtown.shoppingapp.adapters.MainViewPagerAdapter
import org.techtown.shoppingapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMainBinding

    lateinit var mMainAdapter : MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        setupEvents()
        setValues()



    }

    override fun setupEvents() {

        binding.imgCart.setOnClickListener {
            val myIntent = Intent(mContext, CartActivity::class.java)
            startActivity(myIntent)
        }

        binding.imgMyInfo.setOnClickListener {
            val myIntent = Intent(mContext, MyInfoActivity::class.java)
            startActivity(myIntent)
        }


    }

    override fun setValues() {

        val dataArr = arrayOf(
            "가전디지털",
            "뷰티",
            "생활용품",
            "식품",
            "주방용품",
            "캠핑",
            "패션의류/잡화",
            "홈인테리어")

        mMainAdapter = MainViewPagerAdapter(supportFragmentManager, dataArr)
        binding.mainViewPager.adapter = mMainAdapter
        binding.mainViewPager.offscreenPageLimit = 3

        binding.mainTabLayout.setupWithViewPager(binding.mainViewPager)

    }
}