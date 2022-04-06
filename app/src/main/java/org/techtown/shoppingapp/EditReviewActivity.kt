package org.techtown.shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import org.techtown.shoppingapp.databinding.ActivityEditReviewBinding
import org.techtown.shoppingapp.datas.OrderItems

class EditReviewActivity : BaseActivity() {
    lateinit var binding : ActivityEditReviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_review)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        val orderItem = intent.getSerializableExtra("data2") as OrderItems

        Glide.with(mContext).load(orderItem.product.product_main_images[0].image_url).into(binding.productImg)
        binding.productName.text = orderItem.product.name


    }
}