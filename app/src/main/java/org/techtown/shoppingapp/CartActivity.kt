package org.techtown.shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.techtown.shoppingapp.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {

    lateinit var binding : ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cart)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

        binding.btnExit.setOnClickListener {
            finish()
        }

    }

    override fun setValues() {

    }
}