package org.techtown.shoppingapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.adapters.ProductReviewRecyclerAdapter
import org.techtown.shoppingapp.databinding.FragmentProductReviewBinding
import org.techtown.shoppingapp.datas.ProductsResponse

class ProductReviewFragment : BaseFragment() {

    lateinit var binding : FragmentProductReviewBinding
    var ProductId = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_review, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.getInt("product")?.let {
            ProductId = it
        }

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

    }

    override fun setValues() {


    }

    companion object{
        fun newInstance(id : Int): ProductInfoFragment {
            val args = Bundle()
            val fragment = ProductInfoFragment()

            args.putInt("product", id)

            fragment.arguments = args
            return fragment
        }
    }

}