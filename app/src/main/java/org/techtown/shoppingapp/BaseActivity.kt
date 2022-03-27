package org.techtown.shoppingapp

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.techtown.shoppingapp.api.APIList
import org.techtown.shoppingapp.api.ServerAPI

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContext: Context

    lateinit var apiList : APIList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this

        val retrofit = ServerAPI.getRetrofit(mContext)
        apiList = retrofit.create(APIList :: class.java)


    }

    abstract fun setupEvents()

    abstract fun setValues()


}