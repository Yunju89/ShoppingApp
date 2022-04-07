package org.techtown.shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.techtown.shoppingapp.databinding.ActivityShippingBinding

class ShipmentActivity : BaseActivity() {

    lateinit var binding : ActivityShippingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shipping)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}