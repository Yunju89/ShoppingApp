package org.techtown.shoppingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.iamport.sdk.data.sdk.IamPortRequest
import com.iamport.sdk.domain.core.Iamport
import org.json.JSONArray
import org.json.JSONObject
import org.techtown.shoppingapp.adapters.PaymentRecyclerAdapter
import org.techtown.shoppingapp.databinding.ActivityPaymentBinding
import org.techtown.shoppingapp.datas.BasicResponse
import org.techtown.shoppingapp.datas.CartResponse
import org.techtown.shoppingapp.datas.UserAllAddressData
import org.techtown.shoppingapp.interfaces.ShipmentInfoListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class PaymentActivity : BaseActivity(), ShipmentInfoListener {

    lateinit var binding : ActivityPaymentBinding
    lateinit var paymentAdapter : PaymentRecyclerAdapter

    lateinit var getContent : ActivityResultLauncher<Intent>

    var cartList = ArrayList<CartResponse>()
    var shipmentData : UserAllAddressData? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_payment)
        Iamport.init(this)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        val mid_uid = "${Date().time}_${shipmentData?.user_id} "

        binding.btnPayment.setOnClickListener {

            val request = IamPortRequest(
                pg = "nice",
                pay_method = "card",
                name = "상품구매테스트",
                merchant_uid = mid_uid,
                amount = "100",
                buyer_name = "${shipmentData?.name}"
            )
            Iamport.payment("imp26750186", iamPortRequest = request, approveCallback = {

            }, paymentResultCallback = {

                Log.d("yj", "paymentResultCallback ${it.toString()}")

                it?.let {

                    val name = shipmentData?.name ?: ""
                    val address = "${shipmentData?.address1} ${shipmentData?.address2}"
                    val numberZip = shipmentData?.zipcode ?: ""
                    val phoneNum = shipmentData?.phone ?: ""
                    val requestMessage = shipmentData?.memo ?: ""
                    val paymentUid = it.imp_uid!!

                    val buyCartListJsonArr = JSONArray()

                    cartList.forEach {

                            val cartJson = JSONObject()
                            cartJson.put("product_id", it.product_info.id)
                            cartJson.put("quantity", it.quantity)
                            cartJson.put("sale_price", it.product_info.sale_price)

                            val optionJsonArr = JSONArray()

                            for (option in it.option_info) {
                                val optionJsonObj = JSONObject()
                                optionJsonObj.put("option_id", option.option_id)
                                optionJsonObj.put("value_id", option.value_id)
                                optionJsonArr.put(optionJsonObj)
                            }

                            cartJson.put("option_infos", optionJsonArr)

                            buyCartListJsonArr.put(cartJson)

                    }

                    apiList.postRequestOrder(name, address, numberZip, phoneNum, requestMessage, paymentUid, buyCartListJsonArr.toString() ).enqueue(object : Callback<BasicResponse>{
                        override fun onResponse(
                            call: Call<BasicResponse>,
                            response: Response<BasicResponse>
                        ) {
                            if(response.isSuccessful){
                                Toast.makeText(mContext, "구매가 완료되었습니다.", Toast.LENGTH_SHORT).show()

                            }
                            else{
                                Toast.makeText(mContext, "구매에 실패했습니다.", Toast.LENGTH_SHORT).show()

                                val jsonObj = JSONObject(response.errorBody()!!.string())


                                Log.d("yj", "구매실패 ${jsonObj}")
                                Log.d("yj", "구매실패 ${response.code()}")
                            }
                        }

                        override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                            Log.d("yj", "결제통신실패 ${t.message}")
                        }

                    })



                }
                if (it == null) {
                    Toast.makeText(mContext, "결제에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }

            })



        }


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






}