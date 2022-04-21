package org.techtown.shoppingapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import org.json.JSONObject
import org.techtown.shoppingapp.databinding.ActivityMyInfoBinding
import org.techtown.shoppingapp.datas.BasicResponse
import org.techtown.shoppingapp.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyInfoActivity : BaseActivity() {

    lateinit var binding: ActivityMyInfoBinding

    var loginOk = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_info)


        apiList.getRequestMyInfo().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful){
                    loginOk = true
                    if(loginOk){
                        binding.layoutMyInfo.visibility = View.VISIBLE
                        binding.layoutLogin.visibility = View.GONE
                    }
                }
            }
            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
            }
        })

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

        binding.findId.setOnClickListener {
            val myIntent = Intent(mContext, FindEmailActivity::class.java)
            startActivity(myIntent)
            finish()
        }

        binding.findPw.setOnClickListener {
            val myIntent = Intent(mContext, FindPasswordActivity::class.java)
            startActivity(myIntent)
            finish()
        }



        if(ContextUtil.getToken(mContext).isEmpty()){
            binding.layoutLogin.visibility = View.VISIBLE
            binding.layoutMyInfo.visibility = View.GONE

        }

        binding.myReview.setOnClickListener {
            val myIntent = Intent(mContext, MyReviewActivity::class.java)
            startActivity(myIntent)
        }

        binding.myOrder.setOnClickListener {
            val myIntent = Intent(mContext, MyOrderActivity::class.java)
            startActivity(myIntent)
        }

        binding.myInfo.setOnClickListener {
            val myIntent = Intent(mContext,MyInfoDetailActivity::class.java)
            startActivity(myIntent)
        }

        binding.btnLogin.setOnClickListener {

            val inputEmail = binding.edtId.text.toString()
            val inputPw = binding.edtPw.text.toString()

            if(inputEmail.isEmpty()){
                Toast.makeText(mContext, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(inputPw.isEmpty()){
                Toast.makeText(mContext, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            apiList.postRequestLogin(inputEmail, inputPw).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    Log.d("yj", "login ${response.body()?.message}")

                    if (response.isSuccessful) {
                        val br = response.body()!!
                        Toast.makeText(mContext,"${br.data.user.name}님 환영합니다", Toast.LENGTH_SHORT).show()

                        ContextUtil.setToken(mContext,br.data.token)

                        finish()
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

        binding.autoLogin.setOnCheckedChangeListener { buttonView, isChecked ->
            ContextUtil.setAutoLogin(mContext,isChecked)
        }

        binding.logout.setOnClickListener {

            AlertDialog.Builder(this)
                .setTitle("로그아웃")
                .setMessage("정말 로그아웃 하시겠습니까?")
                .setPositiveButton("예", DialogInterface.OnClickListener { dialog, which ->
                    ContextUtil.setToken(mContext,"")
                    binding.layoutLogin.visibility = View.VISIBLE
                    binding.layoutMyInfo.visibility = View.GONE
                })
                .setNegativeButton("아니오", null)
                .show()
        }

    }

    override fun setValues() {

        binding.autoLogin.isChecked = ContextUtil.getAutoLogin(mContext)

    }


}


