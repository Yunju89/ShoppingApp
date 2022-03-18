package org.techtown.shoppingapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.adapters.CategoryAdapter
import org.techtown.shoppingapp.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment() {

    lateinit var binding : FragmentHomeBinding

    lateinit var mCategoryAdapter : CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

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
            "홈인테리어"
        )

        mCategoryAdapter = CategoryAdapter(parentFragmentManager, dataArr)

        binding.homeViewPager.adapter = mCategoryAdapter

        binding.homeTabLayout.setupWithViewPager(binding.homeViewPager)


    }


}