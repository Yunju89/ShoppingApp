package org.techtown.shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.techtown.shoppingapp.databinding.ActivityMyOrderBinding
import org.techtown.shoppingapp.datas.OrderResponse

class MyOrderActivity : BaseActivity() {
    lateinit var binding : ActivityMyOrderBinding
    val mList = ArrayList<OrderResponse>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_my_order)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }





}