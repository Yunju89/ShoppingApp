package org.techtown.shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.techtown.shoppingapp.adapters.PaymentRecyclerAdapter
import org.techtown.shoppingapp.databinding.ActivityPaymentBinding

class PaymentActivity : BaseActivity() {

    lateinit var binding : ActivityPaymentBinding
    lateinit var paymentAdapter : PaymentRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_payment)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {




    }
}