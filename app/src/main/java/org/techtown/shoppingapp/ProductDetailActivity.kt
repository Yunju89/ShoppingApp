package org.techtown.shoppingapp

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import org.techtown.shoppingapp.adapters.ProductDetailViewPagerAdapter
import org.techtown.shoppingapp.adapters.SpinnerProductOptionAdapter
import org.techtown.shoppingapp.databinding.ActivityProductDetailBinding
import org.techtown.shoppingapp.datas.BasicResponse
import org.techtown.shoppingapp.datas.ProductsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat

class ProductDetailActivity : BaseActivity() {
    lateinit var binding: ActivityProductDetailBinding

    lateinit var mProductList : ProductsResponse


    lateinit var mProductInfoAdapter : ProductDetailViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)

        getRequestProductDetailFromServer()

        setupEvents()


    }

    override fun setupEvents() {

    }

    override fun setValues() {


        mProductInfoAdapter = ProductDetailViewPagerAdapter(supportFragmentManager, mProductList)
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
        binding.totalPrice.text = "${myFormat.format(mProductList.sale_price*count)} 원"

        binding.btnCountSub.setOnClickListener {

            if (count == 1 ) {
                Toast.makeText(mContext, "최소 주문 수량은 1개 입니다.", Toast.LENGTH_SHORT).show()
            } else {
                count--
                binding.txtSelectedCount.text = count.toString()
                binding.totalPrice.text = "${myFormat.format(mProductList.sale_price*count)} 원"
            }
        }

        binding.btnCountAdd.setOnClickListener {

            if (count == 5 ) {
                Toast.makeText(mContext, "최대 주문 수량은 5개 입니다.", Toast.LENGTH_SHORT).show()
            } else {
                count++
                binding.txtSelectedCount.text = count.toString()
                binding.totalPrice.text = "${myFormat.format(mProductList.sale_price*count)} 원"
            }
        }

        mProductInfoAdapter.notifyDataSetChanged()

    }

    fun getRequestProductDetailFromServer(){

        val mProductNum = intent.getIntExtra("product", 0)

        apiList.getRequestProductDetail(mProductNum).enqueue(object :Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if(response.isSuccessful){
                    mProductList = response.body()!!.data.product

                    setValues()

                    Log.d("yj", "name : ${mProductList.name}")

                } else {
                    Log.d("yj", "name")
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                Log.d("yj", "onFailure : ${t.message}")
            }
        })

    }

}

