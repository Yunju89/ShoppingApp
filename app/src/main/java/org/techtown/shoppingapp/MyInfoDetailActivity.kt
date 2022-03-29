package org.techtown.shoppingapp

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import okhttp3.ResponseBody
import org.techtown.shoppingapp.databinding.ActivityMyInfoDetailBinding
import org.techtown.shoppingapp.datas.BasicResponse
import org.techtown.shoppingapp.datas.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyInfoDetailActivity : BaseActivity() {

    lateinit var binding : ActivityMyInfoDetailBinding
    lateinit var mUserList : UserData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_info_detail)

        setupEvents()

        getRequestMyInfo()

    }

    override fun setupEvents() {

    }

    override fun setValues() {

        binding.txtEmail.text = mUserList.email
        binding.txtName.text = mUserList.name
        binding.txtPhoneNum.text = mUserList.phone

    }

    fun getRequestMyInfo(){
        apiList.getRequestMyInfoDetail().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful){

                    val br = response.body()!!
                    mUserList = br.data.user

                    setValues()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }
}
