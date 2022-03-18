package org.techtown.shoppingapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.techtown.shoppingapp.fragments.CartFragment
import org.techtown.shoppingapp.fragments.HomeFragment
import org.techtown.shoppingapp.fragments.MyInfoFragment

class MainViewPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {


    override fun getCount() = 3


    override fun getItem(position: Int): Fragment {

        return when(position){
            0 -> HomeFragment()
            1 -> CartFragment()
            else -> MyInfoFragment()

        }
    }




}