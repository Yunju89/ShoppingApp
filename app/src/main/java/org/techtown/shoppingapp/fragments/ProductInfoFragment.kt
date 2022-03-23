package org.techtown.shoppingapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.databinding.FragmentProductInfoBinding
import org.techtown.shoppingapp.databinding.FragmentProductListBinding
import org.techtown.shoppingapp.datas.ProductsResponse

class ProductInfoFragment : BaseFragment() {

    lateinit var binding : FragmentProductInfoBinding

    lateinit var data : ProductsResponse

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

        data = arguments?.getSerializable("product") as ProductsResponse
        Log.d("yj",data.product_infos.size.toString())

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

    }

    override fun setValues() {

//        product_info 개수만큼 forEach 로 View 생성
        data.product_infos.forEach {
            val view = LayoutInflater.from(mContext).inflate(R.layout.product_info,null)
            view.findViewById<TextView>(R.id.desc).text = it.description
            view.findViewById<TextView>(R.id.desc_content).text = it.description_content
            binding.descriptionLayout.addView(view)
        }

        Log.d("yj", "img : ${data.product_detail_images.size}")

//        detail_image 개수만큼 forEach 로 View 생성
        data.product_detail_images.forEach {
            val view = LayoutInflater.from(mContext).inflate(R.layout.product_info_image,null)
            Glide.with(mContext).load(it.image_url).into(view.findViewById(R.id.imgProductInfo))
            binding.descriptionLayout.addView(view)
        }

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