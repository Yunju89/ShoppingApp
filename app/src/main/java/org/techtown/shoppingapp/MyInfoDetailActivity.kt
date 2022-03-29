package org.techtown.shoppingapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.json.JSONObject
import org.techtown.shoppingapp.databinding.ActivityMyInfoDetailBinding
import org.techtown.shoppingapp.datas.BasicResponse
import org.techtown.shoppingapp.datas.UserData
import org.techtown.shoppingapp.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyInfoDetailActivity : BaseActivity() {

    lateinit var binding: ActivityMyInfoDetailBinding
    lateinit var mUserList: UserData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_info_detail)

        setupEvents()

        getRequestMyInfo()

    }

    override fun setupEvents() {

        var checkPw = true

        binding.btnPwChange.setOnClickListener {

            if (checkPw) {
                binding.layoutPwChange.visibility = View.VISIBLE
                binding.btnPwChange.text = "취소"
                checkPw = false
            } else {
                binding.layoutPwChange.visibility = View.GONE
                binding.btnPwChange.text = "변경"
                checkPw = true

                binding.edtPwCheck.setText("")
                binding.edtPw1.setText("")
                binding.edtPw2.setText("")
                binding.txtPwOk.text = "비밀번호를 확인해주세요."
                binding.txtPwOk.setTextColor(Color.GRAY)
            }
        }


        var checkPhone = true

        binding.btnPhoneChange.setOnClickListener {

            if (checkPhone) {
                binding.layoutPhoneChange.visibility = View.VISIBLE
                binding.btnPhoneChange.text = "취소"
                checkPhone = false
            } else {
                binding.layoutPhoneChange.visibility = View.GONE
                binding.btnPhoneChange.text = "변경"
                checkPhone = true

                binding.edtPhone1.setText("")
                binding.edtPhone2.setText("")
                binding.txtPhoneOk.text = "전화번호를 확인해주세요."
                binding.txtPhoneOk.setTextColor(Color.GRAY)

            }
        }

        binding.btnPwSelect.setOnClickListener {
            val inputCheckPw = binding.edtPwCheck.text.toString()
            val inputPw1 = binding.edtPw1.text.toString()
            val inputPw2 = binding.edtPw2.text.toString()

            if ((inputPw1.isNotEmpty() && inputPw2.isNotEmpty() && inputPw1 == inputPw2)){
                apiList.patchRequestPwChange("password",inputPw1,inputCheckPw).enqueue(object : Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if(response.isSuccessful){
                            val br = response.body()!!

                            Toast.makeText(mContext, br.message, Toast.LENGTH_SHORT).show()

                            binding.layoutPwChange.visibility = View.GONE
                            binding.btnPwChange.text = "변경"
                            checkPw = true

                            binding.edtPwCheck.setText("")
                            binding.edtPw1.setText("")
                            binding.edtPw2.setText("")
                            binding.txtPwOk.text = ""

                            if(br.data.token.isNotEmpty()){
                                ContextUtil.setToken(mContext,br.data.token)
                            }


                            getRequestMyInfo()

                        }
                        else{
                            val jsonObj = JSONObject(response.errorBody()!!.string())
                            val br = jsonObj.getString("message")
                            Toast.makeText(mContext, br, Toast.LENGTH_SHORT).show()
                        }

                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }

                })
            }
            else{
                binding.txtPwOk.setTextColor(Color.RED)
                binding.txtPwOk.text = "입력 값이 올바르지 않습니다."
            }

        }

        binding.btnPhoneSelect.setOnClickListener {

            val inputPhone1 = binding.edtPhone1.text.toString()
            val inputPhone2 = binding.edtPhone2.text.toString()

            Log.d("yj", "input : ${inputPhone1}")
            if (inputPhone1.isNotEmpty() && inputPhone2.isNotEmpty() && inputPhone1 == inputPhone2) {

                apiList.patchRequestChange("phone", inputPhone1)
                    .enqueue(object : Callback<BasicResponse> {
                        override fun onResponse(
                            call: Call<BasicResponse>,
                            response: Response<BasicResponse>
                        ) {
                            if (response.isSuccessful) {
                                val br = response.body()!!
                                Log.d("yj", "change : ${br.message}")
                                Toast.makeText(mContext, br.message, Toast.LENGTH_SHORT).show()

                                binding.edtPhone1.setText("")
                                binding.edtPhone2.setText("")
                                binding.txtPhoneOk.text = ""

                                checkPhone = true


                                binding.layoutPhoneChange.visibility = View.GONE
                                binding.btnPhoneChange.text = "변경"

                                getRequestMyInfo()

                            } else {
                                Log.d("yj", "수정실패")
                            }
                        }

                        override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                            Log.d("yj", "t : ${t.message}")
                        }

                    })


            } else {
                binding.txtPhoneOk.setTextColor(Color.RED)
                binding.txtPhoneOk.text = "입력 값이 올바르지 않습니다."

            }

        }

    }

    override fun setValues() {

        binding.txtEmail.text = mUserList.email
        binding.txtName.text = mUserList.name
        binding.txtPhoneNum.text = mUserList.phone

    }

    fun getRequestMyInfo() {
        apiList.getRequestMyInfoDetail().enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {

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
