package org.techtown.shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.techtown.shoppingapp.databinding.ActivityFindPasswordBinding

class FindPasswordActivity : AppCompatActivity() {

    lateinit var binding : ActivityFindPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_find_password)
    }
}