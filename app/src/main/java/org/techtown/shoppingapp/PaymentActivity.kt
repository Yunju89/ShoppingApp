package org.techtown.shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import org.techtown.shoppingapp.adapters.PaymentRecyclerAdapter
import org.techtown.shoppingapp.databinding.ActivityPaymentBinding
import org.techtown.shoppingapp.datas.BasicResponse
import org.techtown.shoppingapp.datas.CartResponse
import org.techtown.shoppingapp.datas.DataResponse
import org.techtown.shoppingapp.datas.UserAllAddressData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentActivity : BaseActivity() {

    lateinit var binding : ActivityPaymentBinding
    lateinit var paymentAdapter : PaymentRecyclerAdapter

    var cartList = ArrayList<CartResponse>()
    lateinit var shipmentData : DataResponse


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_payment)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        cartList = intent.getSerializableExtra("cartList") as ArrayList<CartResponse>

        Log.d("yj", "CartList : ${cartList.size}")

        apiList.getRequestShipmentInfo().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful){
                    val br = response.body()!!

                    shipmentData = br.data

                    Log.d("yj", "shipmentDataSize ${shipmentData.user_all_address.size}")



                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })






    }
}