package org.techtown.shoppingapp

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import org.techtown.shoppingapp.adapters.SpinnerProductOptionAdapter
import org.techtown.shoppingapp.databinding.ActivityProductDetailBinding
import org.techtown.shoppingapp.datas.ProductsResponse
import java.text.DecimalFormat

class ProductDetailActivity : BaseActivity() {
    lateinit var binding: ActivityProductDetailBinding

    lateinit var mProductList: ProductsResponse

    lateinit var mSpinnerAdapter: SpinnerProductOptionAdapter

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


        val myFormat = DecimalFormat("###,###")

        Glide.with(mContext).load(mProductList.product_main_images[0].image_url)
            .into(binding.mainImage)
        binding.txtSmallCategory.text = mProductList.small_category_info.name
        binding.txtProductName.text = mProductList.name
        binding.txtSalePrice.text = myFormat.format(mProductList.sale_price).toString()
        binding.txtOriginalPrice.text = myFormat.format(mProductList.original_price).toString()
        binding.txtOriginalPrice.paintFlags =
            binding.txtOriginalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG


        when (mProductList.product_options.size) {
            0 -> {
                binding.layoutOption1.visibility = View.GONE
                binding.layoutOption2.visibility = View.GONE
            }
            1 -> {
                binding.layoutOption2.visibility = View.GONE
                binding.txtOptionValue.text = mProductList.product_options[0].name

            }
            2 -> {
                binding.txtOptionValue.text = mProductList.product_options[0].name
                binding.txtOptionValue2.text = mProductList.product_options[1].name
            }
        }

        if (mProductList.product_options.size > 0) {

            mSpinnerAdapter = SpinnerProductOptionAdapter(
                mContext,
                R.layout.spinner_product_option,
                mProductList.product_options[0].option_values
            )
            binding.spinnerSelected1.adapter = mSpinnerAdapter
        }

        if (mProductList.product_options.size > 1) {
            mSpinnerAdapter = SpinnerProductOptionAdapter(
                mContext,
                R.layout.spinner_product_option,
                mProductList.product_options[1].option_values
            )
            binding.spinnerSelected2.adapter = mSpinnerAdapter

        }

        var count = 1
        binding.btnCountSub.setOnClickListener {

            if (count <= 1 ) {
                Toast.makeText(mContext, "최소 주문 수량은 1개 입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            } else {
                binding.txtSelectedCount.text = count--.toString()
            }
        }

        binding.btnCountAdd.setOnClickListener {

            if(count >= 6 ) {
                Toast.makeText(mContext, "최대 주문 수량은 5개 입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                binding.txtSelectedCount.text = count++.toString()
            }
        }

    }


}

