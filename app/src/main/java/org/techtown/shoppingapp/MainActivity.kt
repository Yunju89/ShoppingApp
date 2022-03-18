package org.techtown.shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import org.techtown.shoppingapp.adapters.MainViewPagerAdapter
import org.techtown.shoppingapp.databinding.ActivityMainBinding
import org.techtown.shoppingapp.fragments.HomeFragment

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

        binding.mainBottomNav.setOnItemSelectedListener {

            when (it.itemId){
                R.id.home -> binding.mainViewPager.currentItem = 0
                R.id.cart -> binding.mainViewPager.currentItem = 1
                R.id.myInfo -> binding.mainViewPager.currentItem = 2
            }
            return@setOnItemSelectedListener true
        }

        binding.mainViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                binding.mainBottomNav.menu.getItem(position).isChecked = true
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })

    }

    override fun setValues() {

        mMainAdapter = MainViewPagerAdapter(supportFragmentManager)
        binding.mainViewPager.adapter = mMainAdapter
        binding.mainViewPager.offscreenPageLimit = 3

    }
}