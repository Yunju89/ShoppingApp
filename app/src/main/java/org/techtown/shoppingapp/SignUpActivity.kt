package org.techtown.shoppingapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.gson.JsonObject
import org.json.JSONObject
import org.techtown.shoppingapp.databinding.ActivitySignUpBinding
import org.techtown.shoppingapp.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity() {
    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        binding.btnSignUp.setOnClickListener {

            val inputEmail = binding.edtEmail.text.toString()
            val inputPw = binding.edtPw.text.toString()
            val inputName = binding.edtName.text.toString()
            val inputPhone = binding.edtPhoneNum.text.toString()

            apiList.putRequestSignUp(inputEmail, inputPw, inputName, inputPhone)
                .enqueue(object : Callback<BasicResponse> {
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful) {
                            val br = response.body()!!
                            Log.d("yj", "sighUp ${br.message}")
                            Toast.makeText(mContext, "${br.message}", Toast.LENGTH_SHORT).show()

                            finish()

                            
                        } else {
                            val jsonObj = JSONObject(response.errorBody()!!.string())
                                val br =jsonObj.getString("message")
                                Log.d("yj", "sighUpFail $br")

                            Toast.makeText(mContext, br, Toast.LENGTH_SHORT).show()
                            }
                        }


                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                        Log.d("yj", "sighUpServerFail ${t.message}")
                    }

                })
        }


    }
}