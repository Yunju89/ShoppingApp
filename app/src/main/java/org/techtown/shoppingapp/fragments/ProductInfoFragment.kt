package org.techtown.shoppingapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.databinding.FragmentProductInfoBinding
import org.techtown.shoppingapp.databinding.FragmentProductListBinding
import org.techtown.shoppingapp.datas.ProductsResponse

class ProductInfoFragment : BaseFragment() {

    lateinit var binding : FragmentProductInfoBinding

    lateinit var mProductList: ProductsResponse

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_info, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupEvents()
        setValues()

        val data = arguments?.getSerializable("product") as ProductsResponse
        Log.d("yj", "images : ${data.product_main_images.size}")
        Log.d("yj", "info : ${data.product_infos.size}")
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        
        
        
    }

    companion object{
        fun newInstance(data: ProductsResponse): ProductInfoFragment{
            val args = Bundle()
            val fragment = ProductInfoFragment()

            args.putSerializable("product", data)

            fragment.arguments = args
            return fragment
        }
    }

}