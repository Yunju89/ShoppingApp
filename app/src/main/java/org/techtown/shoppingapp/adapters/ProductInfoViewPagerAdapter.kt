package org.techtown.shoppingapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.techtown.shoppingapp.datas.ProductsResponse
import org.techtown.shoppingapp.fragments.ProductInfoFragment
import org.techtown.shoppingapp.fragments.ProductReviewFragment

class ProductInfoViewPagerAdapter(
    fm : FragmentManager,
    val mData : ProductsResponse
) : FragmentPagerAdapter(fm){


    override fun getCount() = 2

    override fun getItem(position: Int): Fragment {

        val id = mData.id

        return when(position){
            0 ->ProductInfoFragment.newInstance(mData)
            else -> ProductReviewFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "상품 정보"
            else -> "리뷰 목록"
        }
    }
}