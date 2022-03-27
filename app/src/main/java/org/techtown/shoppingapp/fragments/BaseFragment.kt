package org.techtown.shoppingapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import org.techtown.shoppingapp.api.APIList
import org.techtown.shoppingapp.api.ServerAPI
import retrofit2.Retrofit

abstract class BaseFragment : Fragment() {

    lateinit var mContext: Context

    lateinit var apiList : APIList

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mContext = requireContext()

        val retrofit = ServerAPI.getRetrofit(mContext)          // apiList 쓰기위해 retrofit 객체생성
        apiList = retrofit.create(APIList :: class.java)



    }

    abstract fun setupEvents()

    abstract fun setValues()

}