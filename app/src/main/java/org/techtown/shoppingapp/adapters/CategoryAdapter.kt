package org.techtown.shoppingapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.techtown.shoppingapp.fragments.ProductListFragment

class CategoryAdapter(
    fm: FragmentManager,
    val mList: Array<String>
) : FragmentPagerAdapter(fm) {

    override fun getCount() = mList.size

    override fun getItem(position: Int): Fragment {
        return ProductListFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mList[position]
    }


}