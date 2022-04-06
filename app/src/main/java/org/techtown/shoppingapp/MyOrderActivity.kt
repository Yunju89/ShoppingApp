package org.techtown.shoppingapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.shoppingapp.adapters.OrderListTitleRecyclerAdapter
import org.techtown.shoppingapp.databinding.ActivityMyOrderBinding
import org.techtown.shoppingapp.datas.BasicResponse
import org.techtown.shoppingapp.datas.OrderResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyOrderActivity : BaseActivity() {
    lateinit var binding: ActivityMyOrderBinding
    val mList = ArrayList<OrderResponse>()
    lateinit var mOrderAdapter : OrderListTitleRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_order)

        setupEvents()
        getRequestOrder()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        mOrderAdapter = OrderListTitleRecyclerAdapter(mList)
        binding.orderRecyclerView.adapter = mOrderAdapter
        binding.orderRecyclerView.layoutManager = LinearLayoutManager(mContext)




    }

    fun getRequestOrder() {
        apiList.getRequestOrderList().enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    val br = response.body()!!
                    mList.clear()
                    mList.addAll(br.data.getOrderList())

                    setValues()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                Log.d("yj", t.message.toString())
            }

        })
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK) {
            getRequestOrder()
        }

    }


}