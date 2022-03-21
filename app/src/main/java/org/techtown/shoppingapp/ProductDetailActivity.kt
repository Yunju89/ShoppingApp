package org.techtown.shoppingapp

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import org.techtown.shoppingapp.databinding.ActivityProductDetailBinding
import org.techtown.shoppingapp.datas.ProductsResponse
import java.text.DecimalFormat

class ProductDetailActivity : BaseActivity() {
    lateinit var binding : ActivityProductDetailBinding

    lateinit var mProductList : ProductsResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_product_detail)

        mProductList = intent.getSerializableExtra("product") as ProductsResponse

        setupEvents()
        setValues()




    }

    override fun setupEvents() {

    }

    override fun setValues() {

        val myFormat = DecimalFormat("###,###")

        Glide.with(mContext).load(mProductList.product_main_images[0].image_url).into(binding.mainImage)
        binding.txtSmallCategory.text = mProductList.small_category_info.name
        binding.txtProductName.text = mProductList.name
        binding.txtSalePrice.text = myFormat.format(mProductList.sale_price).toString()
        binding.txtOriginalPrice.text = myFormat.format(mProductList.original_price).toString()
        binding.txtOriginalPrice.paintFlags = binding.txtOriginalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        when(mProductList.product_options.size){
            1 -> {binding.LayoutOption1.visibility
                binding.txtOptionValue.text = mProductList.product_options[0].name }

            2 -> {binding.layoutOption2.visibility
                binding.txtOptionValue2.text = mProductList.product_options[1].name} }




    }
}