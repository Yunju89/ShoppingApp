package org.techtown.shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.techtown.shoppingapp.databinding.ActivityFindEmailBinding

class FindEmailActivity : AppCompatActivity() {

    lateinit var binding : ActivityFindEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_find_email)
    }
}