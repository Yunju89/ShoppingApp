package org.techtown.shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import org.techtown.shoppingapp.databinding.ActivityRegistReviewBinding
import org.techtown.shoppingapp.datas.OrderItems

class RegistReviewActivity : BaseActivity() {

    lateinit var binding : ActivityRegistReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_regist_review)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

    }

    override fun setValues() {
        val orderItem = intent.getSerializableExtra("data") as OrderItems

        Log.d("yj", orderItem.product.name)

        Glide.with(mContext).load(orderItem.product.product_main_images[0].image_url).into(binding.productImg)
        binding.productName.text = orderItem.product.name



    }
}