package org.techtown.shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.shoppingapp.adapters.CartListRecyclerAdapter
import org.techtown.shoppingapp.databinding.ActivityCartBinding
import org.techtown.shoppingapp.datas.BasicResponse
import org.techtown.shoppingapp.datas.CartResponse
import org.techtown.shoppingapp.viewholder.CartListViewHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartActivity : BaseActivity() {

    lateinit var binding : ActivityCartBinding

    lateinit var mCartListAdapter : CartListRecyclerAdapter

    val mList = ArrayList<CartResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cart)


        getRequestCartFromServer()
        setupEvents()
        setValues()


    }

    override fun setupEvents() {

        binding.btnExit.setOnClickListener {
            finish()
        }

    }

    override fun setValues() {
        mCartListAdapter = CartListRecyclerAdapter(mList)
        binding.cartItem.adapter = mCartListAdapter
        binding.cartItem.layoutManager = LinearLayoutManager(mContext)


    }

    fun getRequestCartFromServer(){
        apiList.getRequestMyCart().enqueue(object :Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful){
                    val br = response.body()!!

                    mList.clear()
                    mList.addAll(br.data.carts)

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

}