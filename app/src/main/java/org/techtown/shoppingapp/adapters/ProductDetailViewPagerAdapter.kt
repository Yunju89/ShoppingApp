package org.techtown.shoppingapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.techtown.shoppingapp.datas.ProductsResponse
import org.techtown.shoppingapp.fragments.ProductInfoFragment
import org.techtown.shoppingapp.fragments.ProductReviewFragment

class ProductDetailViewPagerAdapter(
    fm : FragmentManager,
    val mData : ProductsResponse
) : FragmentPagerAdapter(fm){


    override fun getCount() = 2

    override fun getItem(position: Int): Fragment {

        return when(position){
            0 -> ProductInfoFragment.newInstance(mData)
            1 -> ProductReviewFragment.newInstance(mData)
            else -> ProductInfoFragment.newInstance(mData)

        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "상품 정보"
            else -> "리뷰 목록"
        }
    }
}