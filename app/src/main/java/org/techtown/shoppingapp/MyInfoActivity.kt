package org.techtown.shoppingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import org.json.JSONObject
import org.techtown.shoppingapp.databinding.ActivityMyInfoBinding
import org.techtown.shoppingapp.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyInfoActivity : BaseActivity() {

    lateinit var binding: ActivityMyInfoBinding

    var LoginOk = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_info)

        setupEvents()
        setValues()



    }

    override fun setupEvents() {


    }

    override fun setValues() {

        binding.btnLogin.setOnClickListener {

            val inputEmail = binding.edtId.text.toString()
            val inputPw = binding.edtPw.text.toString()

            apiList.postRequestLogin(inputEmail, inputPw).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    Log.d("yj", "login ${response.body()?.message}")

                    if (response.isSuccessful) {
                        val br = response.body()!!
                        Toast.makeText(mContext,"${br.data.user.name}님 환영합니다", Toast.LENGTH_SHORT).show()

                        LoginOk = true

                        if(LoginOk){
                            binding.layoutLogin.visibility = View.GONE
                        }

                    }
                    else {

                        val jsonObj = JSONObject(response.errorBody()!!.string())
                        val br =jsonObj.getString("message")
                        Log.d("yj", "LoginFail $br")

                        Toast.makeText(mContext, br, Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    Log.d("yj", "loginFail ${t.message}")
                }
            })
        }




        binding.btnSignUp.setOnClickListener {

            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)

        }






    }


}


