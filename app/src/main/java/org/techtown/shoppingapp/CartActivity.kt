package org.techtown.shoppingapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.shoppingapp.interfaces.CartItemDeletedListener
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

    var checkboxArr : ArrayList<String> = arrayListOf()

    var checkList = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cart)


        getRequestCartFromServer()
        setupEvents()



    }

    override fun setupEvents() {

        binding.btnCartOrder.setOnClickListener {
            val myIntent = Intent(mContext, PaymentActivity::class.java)
            myIntent.putExtra("cartList", mList)
            startActivity(myIntent)
        }

        binding.btnExit.setOnClickListener {
            finish()
        }

        binding.btnDel.setOnClickListener {

            checkList = checkboxArr.joinToString()

            Log.d("yj","check : ${checkList}")

            AlertDialog.Builder(mContext)
                .setTitle("알림")
                .setMessage("선택한 상품을 장바구니에서 삭제할까요?")
                .setPositiveButton("예", DialogInterface.OnClickListener { dialog, which ->
                    apiList.deleteCartList(checkList).enqueue(object : Callback<BasicResponse>{
                        override fun onResponse(
                            call: Call<BasicResponse>,
                            response: Response<BasicResponse>
                        ) {
                            if(response.isSuccessful){

                                checkboxArr.clear()
                                checkList = ""
                                getRequestCartFromServer()
                            }
                        }

                        override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                        }
                    })
                })
                .setNegativeButton("아니오", null)
                .show()



        }




    }

    override fun setValues() {
        mCartListAdapter = CartListRecyclerAdapter(mList, this)
        binding.cartItem.adapter = mCartListAdapter


        var totalPrice = 0

        mList.forEach {

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

        AlertDialog.Builder(this)
            .setTitle("알림")
            .setMessage("상품을 장바구니에서 삭제할까요?")
            .setPositiveButton("예", DialogInterface.OnClickListener { dialog, which ->
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
            })
            .setNegativeButton("아니오", null)
            .show()




    }

    override fun selectedCheckBox(data: CartResponse, isChecked: Boolean) {

        var checked = false

        checkboxArr.forEach {
            if (it == data.id.toString()) {
                checked = true
            }
        }

        if(isChecked){

            if (!checked) {
                checkboxArr.add(data.id.toString())
            }
        } else {

            if (checked) {
                checkboxArr.remove(data.id.toString())
            }
        }


    }

}







