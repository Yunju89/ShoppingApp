package org.techtown.shoppingapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.techtown.shoppingapp.datas.LargeCategoriesData
import org.techtown.shoppingapp.fragments.ProductListFragment

class MainViewPagerAdapter(
    fm: FragmentManager,
    val mList: ArrayList<LargeCategoriesData>
) : FragmentPagerAdapter(fm) {

    override fun getCount() = mList.size

    override fun getItem(position: Int): Fragment {
        return ProductListFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {

        return mList[position].name


    }


}