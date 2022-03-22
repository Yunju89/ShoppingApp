package org.techtown.shoppingapp

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import org.techtown.shoppingapp.adapters.ProductInfoViewPagerAdapter
import org.techtown.shoppingapp.adapters.SpinnerProductOptionAdapter
import org.techtown.shoppingapp.databinding.ActivityProductDetailBinding
import org.techtown.shoppingapp.datas.ProductsResponse
import java.text.DecimalFormat

class ProductDetailActivity : BaseActivity() {
    lateinit var binding: ActivityProductDetailBinding

    lateinit var mProductList: ProductsResponse

    lateinit var mProductInfoAdapter : ProductInfoViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)

        mProductList = intent.getSerializableExtra("product") as ProductsResponse



        setupEvents()
        setValues()


    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mProductInfoAdapter = ProductInfoViewPagerAdapter(supportFragmentManager, mProductList)
        binding.ViewPagerDetailInfo.adapter = mProductInfoAdapter
        binding.tabLayoutDetailInfo.setupWithViewPager(binding.ViewPagerDetailInfo)


        val myFormat = DecimalFormat("###,###")

        Glide.with(mContext).load(mProductList.product_main_images[0].image_url)
            .into(binding.mainImage)
        binding.txtSmallCategory.text = mProductList.small_category_info.name
        binding.txtProductName.text = mProductList.name
        binding.txtSalePrice.text = myFormat.format(mProductList.sale_price).toString()
        binding.txtOriginalPrice.text = myFormat.format(mProductList.original_price).toString()
        binding.txtOriginalPrice.paintFlags =
            binding.txtOriginalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG



       mProductList.product_options.forEach {
           val view = LayoutInflater.from(mContext).inflate(R.layout.product_option, null, false)
           view.findViewById<TextView>(R.id.txtOptionValue).text = it.name
           view.findViewById<Spinner>(R.id.spinnerSelected).adapter = SpinnerProductOptionAdapter(mContext, R.layout.spinner_product_option, it.option_values)
           binding.layoutOption.addView(view)
       }


        var count = 1
        binding.btnCountSub.setOnClickListener {

            if (count == 1 ) {
                Toast.makeText(mContext, "최소 주문 수량은 1개 입니다.", Toast.LENGTH_SHORT).show()
            } else {
                count--
                binding.txtSelectedCount.text = count.toString()
            }
        }

        binding.btnCountAdd.setOnClickListener {

            if (count == 5 ) {
                Toast.makeText(mContext, "최대 주문 수량은 5개 입니다.", Toast.LENGTH_SHORT).show()
            } else {
                count++
                binding.txtSelectedCount.text = count.toString()
            }
        }

    }


}

