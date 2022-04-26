package org.techtown.shoppingapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
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

    lateinit var binding: ActivityCartBinding

    lateinit var mCartListAdapter: CartListRecyclerAdapter

    val mList = ArrayList<CartResponse>()

    var mOrderList: ArrayList<CartResponse> = arrayListOf()

    var checkboxArr: ArrayList<String> = arrayListOf()

    var checkList = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart)


        getRequestCartFromServer()
        setupEvents()


    }

    override fun setupEvents() {

        binding.allCheckBox.setOnClickListener {

            it.isSelected = !it.isSelected

            mCartListAdapter.checkbox(it.isSelected)

            mOrderList.clear()

            if (it.isSelected) {
                mOrderList.addAll(mList)
            }

            Log.d("yj", "mOrderList : ${mOrderList.size} ")

        }


        binding.btnCartOrder.setOnClickListener {

            if (mOrderList.size == 0) {
                Toast.makeText(mContext, "선택된 상품이 없습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val myIntent = Intent(mContext, PaymentActivity::class.java)
            myIntent.putExtra("cartList", mOrderList)
            startActivity(myIntent)
        }

        binding.btnExit.setOnClickListener {
            finish()
        }

        binding.btnDel.setOnClickListener {

            mOrderList.forEach {
                val checkId = it.id.toString()
                checkboxArr += checkId
            }

            checkList = checkboxArr.joinToString()

            Log.d("yj", "check : ${checkList}")

            if (checkList.isEmpty()) {
                Toast.makeText(mContext, "선택 된 상품이 없습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            AlertDialog.Builder(mContext)
                .setTitle("알림")
                .setMessage("선택한 상품을 장바구니에서 삭제할까요?")
                .setPositiveButton("예", DialogInterface.OnClickListener { dialog, which ->
                    apiList.deleteCartList(checkList).enqueue(object : Callback<BasicResponse> {
                        override fun onResponse(
                            call: Call<BasicResponse>,
                            response: Response<BasicResponse>
                        ) {
                            if (response.isSuccessful) {

                                mOrderList.clear()
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
            val price = quantity * itemPrice
            totalPrice += price
        }

        val myFormat = DecimalFormat("###,###")

        binding.totalPrice.text = myFormat.format(totalPrice).toString()

        var shippingFee = 3000

        if (totalPrice >= 30000) {
            shippingFee = 0
        }

        binding.shippingFee.text = myFormat.format(shippingFee).toString()


        binding.allPrice.text = myFormat.format(totalPrice + shippingFee).toString()


    }

    fun getRequestCartFromServer() {
        apiList.getRequestMyCart().enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    val br = response.body()!!

                    mList.clear()
                    mList.addAll(br.data.carts)

                    setValues()

                    mCartListAdapter.notifyDataSetChanged()

                } else {
                    Log.d("yj", "cart 실패")
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
                apiList.deleteCart(data.id.toString()).enqueue(object : Callback<BasicResponse> {
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful) {
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


        if (isChecked) {

            mOrderList.add(data)

        } else {
            mOrderList.remove(data)

        }


        binding.allCheckBox.isSelected = mOrderList.size == mList.size

        Log.d("yj", "mOrderList : ${mOrderList.size} ")

    }

}







