package org.techtown.shoppingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.google.gson.JsonObject
import org.json.JSONObject
import org.techtown.shoppingapp.databinding.ActivityFindEmailBinding
import org.techtown.shoppingapp.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindEmailActivity : BaseActivity() {

    lateinit var binding : ActivityFindEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_find_email)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.btnFindId.setOnClickListener {

            val inputName = binding.edtName.text.toString()
            val inputPhone = binding.edtPhone.text.toString()

            apiList.getRequestUserEmailFind(inputName,inputPhone).enqueue(object :Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if(response.isSuccessful){
                        binding.layoutFindId.isVisible = true

                        response.body()?.let {
                            binding.txtFindId.text = it.data.email
                        }

                    }
                    else {
                        val jsonObj = JSONObject(response.errorBody()!!.string())
                        val message = jsonObj.getString("message")

                        Log.d("yj", "findId $message")
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                    Log.d("yj", "findIdFail ${t.message}")
                }

            })
        }

        binding.btnLogin.setOnClickListener {
            val myIntent = Intent(mContext, MyInfoActivity::class.java)
            startActivity(myIntent)
            finish()
        }

        binding.btnFindPw.setOnClickListener {
            val myIntent = Intent(mContext, FindPasswordActivity::class.java)
            startActivity(myIntent)
            finish()
        }


    }


    override fun setValues() {

    }
}