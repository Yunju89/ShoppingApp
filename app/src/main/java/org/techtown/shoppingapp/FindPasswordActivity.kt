package org.techtown.shoppingapp

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import org.json.JSONObject
import org.techtown.shoppingapp.databinding.ActivityFindPasswordBinding
import org.techtown.shoppingapp.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindPasswordActivity : BaseActivity() {

    lateinit var binding : ActivityFindPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_find_password)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnFindPw.setOnClickListener {

            val inputEmail = binding.edtEmail.text.toString()
            val inputName = binding.edtName.text.toString()
            val inputPhone = binding.edtPhone.text.toString()

            apiList.getRequestUserPasswordFind(inputEmail,inputName,inputPhone).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            binding.layoutFindPw.isVisible = true
                            binding.txtFindPw.text = it.data.password
                        }
                    }
                    else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        val message = jsonObj.getString("message")

                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })

        }

        binding.btnLogin.setOnClickListener {
            val myIntent = Intent(mContext, MyInfoActivity::class.java)
            startActivity(myIntent)
            finish()
        }


    }

    override fun setValues() {

    }
}