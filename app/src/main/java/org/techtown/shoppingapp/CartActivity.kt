package org.techtown.shoppingapp

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.shoppingapp.`interface`.CartItemDeletedListener
import org.techtown.shoppingapp.adapters.CartListRecyclerAdapter
import org.techtown.shoppingapp.databinding.ActivityCartBinding
import org.techtown.shoppingapp.datas.BasicResponse
import org.techtown.shoppingapp.datas.CartResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat

class CartActivity : BaseActivity(), CartItemDeletedListener {

    lateinit var binding : ActivityCartBinding

    lateinit var mCartListAdapter : CartListRecyclerAdapter

    val mList = ArrayList<CartResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cart)


        getRequestCartFromServer()
        setupEvents()



    }

    override fun setupEvents() {

        binding.btnExit.setOnClickListener {
            finish()
        }

    }

    override fun setValues() {
        mCartListAdapter = CartListRecyclerAdapter(mList, this)
        binding.cartItem.adapter = mCartListAdapter
        binding.cartItem.layoutManager = LinearLayoutManager(mContext)


        var totalPrice = 0

        mList.forEach {
            Log.d("yj", "quantity${it.quantity}")
            Log.d("yj", "quantity${it.product_info.sale_price}")
            val quantity = it.quantity
            val itemPrice = it.product_info.sale_price
            val price = quantity*itemPrice
            totalPrice += price
        }

        val myFormat = DecimalFormat("###,###")

        binding.totalPrice.text = myFormat.format(totalPrice).toString()

        var shippingFee = 3000

        if(totalPrice>=100000){
            shippingFee = 0
        }

        binding.shippingFee.text = myFormat.format(shippingFee).toString()


        binding.allPrice.text = myFormat.format(totalPrice+shippingFee).toString()






    }

    fun getRequestCartFromServer(){
        apiList.getRequestMyCart().enqueue(object :Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful){
                    val br = response.body()!!

                    mList.clear()
                    mList.addAll(br.data.carts)

                    setValues()

                    mCartListAdapter.notifyDataSetChanged()

                }
                else{
                    Log.d("yj","cart 실패")
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })



    }

    override fun onDeletedItem(data: CartResponse) {

        apiList.deleteCart(data.id.toString()).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(
                call: Call<BasicResponse>,
                response: Response<BasicResponse>
            ) {
                if(response.isSuccessful){
                    getRequestCartFromServer()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })

    }

    override fun selectedCheckBox(data: CartResponse, isChecked: Boolean) {

    }

}