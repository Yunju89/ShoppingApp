package org.techtown.shoppingapp

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import org.json.JSONArray
import org.json.JSONObject
import org.techtown.shoppingapp.adapters.ProductDetailViewPagerAdapter
import org.techtown.shoppingapp.adapters.SpinnerProductOptionAdapter
import org.techtown.shoppingapp.databinding.ActivityProductDetailBinding
import org.techtown.shoppingapp.datas.BasicResponse
import org.techtown.shoppingapp.datas.OptionValuesData
import org.techtown.shoppingapp.datas.ProductsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat

class ProductDetailActivity : BaseActivity() {
    lateinit var binding: ActivityProductDetailBinding

    lateinit var mProductList : ProductsResponse

    lateinit var mProductInfoAdapter : ProductDetailViewPagerAdapter

    var selectedList : ArrayList<OptionValuesData> = arrayListOf()

    var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)

        getRequestProductDetailFromServer()

        setupEvents()


    }

    override fun setupEvents() {

        binding.btnCart.setOnClickListener {

            val selectOptionArr = JSONArray()

            selectedList.forEach {
                val jsonObj = JSONObject()
                jsonObj.put("option_id", it.option_id)
                jsonObj.put("selected_value_id",it.id)

                selectOptionArr.put(jsonObj)
            }

            apiList.postRequestCartAdd(
                mProductList.id,count,selectOptionArr.toString()
            ).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    Log.d("yj","count : ${count}")

                    if(response.isSuccessful){
                        Toast.makeText(mContext, "장바구니에 물건이 담겼습니다.", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(mContext, "장바구니 등록에 실패했습니다. 관리자에게 문의하세요.", Toast.LENGTH_SHORT).show()
                    }
                    
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                    
                }

            })






        }

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
           selectedList.add(it.option_values[0])

           val view = LayoutInflater.from(mContext).inflate(R.layout.product_option, null, false)
           view.findViewById<TextView>(R.id.txtOptionValue).text = it.name

           view.findViewById<Spinner>(R.id.spinnerSelected).adapter = SpinnerProductOptionAdapter(mContext, R.layout.spinner_product_option, it.option_values)
           view.findViewById<Spinner>(R.id.spinnerSelected).onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
               override fun onItemSelected(
                   parent: AdapterView<*>?,
                   view: View?,
                   position: Int,
                   id: Long
               ) {
                   val mOptionValue = it.option_values[position]

                   var position = -1
                   selectedList.forEachIndexed { index, optionValuesData ->

                       if(mOptionValue.option_id == optionValuesData.option_id){
                           position = index
                       }
                   }

                   if(position >= 0) {
                       selectedList.removeAt(position)
                       selectedList.add(mOptionValue)
                   }
               }

               override fun onNothingSelected(parent: AdapterView<*>?) {

               }

           }

           binding.layoutOption.addView(view)
       }


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

