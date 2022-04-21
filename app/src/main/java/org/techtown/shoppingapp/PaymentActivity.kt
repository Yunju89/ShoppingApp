package org.techtown.shoppingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import org.techtown.shoppingapp.adapters.PaymentRecyclerAdapter
import org.techtown.shoppingapp.databinding.ActivityPaymentBinding
import org.techtown.shoppingapp.datas.BasicResponse
import org.techtown.shoppingapp.datas.CartResponse
import org.techtown.shoppingapp.datas.UserAllAddressData
import org.techtown.shoppingapp.interfaces.ShipmentInfoListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentActivity : BaseActivity(), ShipmentInfoListener {

    lateinit var binding : ActivityPaymentBinding
    lateinit var paymentAdapter : PaymentRecyclerAdapter

    lateinit var getContent : ActivityResultLauncher<Intent>

    var cartList = ArrayList<CartResponse>()
    var shipmentData : UserAllAddressData? = null


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


        apiList.getRequestShipmentInfo().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if(response.isSuccessful){
                    val br = response.body()!!

                    shipmentData = br.data.getSelectAddress()

                }

                setPaymentRecyclerView()
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })

        getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                shipmentData = it.data?.extras?.get("shipmentInfo") as UserAllAddressData
                setPaymentRecyclerView()
            }
        }


    }

    fun setPaymentRecyclerView(){

        paymentAdapter = PaymentRecyclerAdapter(shipmentData, cartList, this)
        binding.paymentRecyclerView.adapter = paymentAdapter



    }

    override fun onClickShipmentInfo() {


        val myIntent = Intent(mContext, MyShipmentInfoActivity::class.java)
        getContent.launch(myIntent)
    }





    override fun onResume() {
        super.onResume()

        Log.d("yj", "onResume 실행")
    }

    override fun onPause() {
        super.onPause()

        Log.d("yj", "onPause 실행")
    }


}