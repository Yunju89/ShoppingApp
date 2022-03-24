package org.techtown.shoppingapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.databinding.DataBindingUtil
import org.json.JSONObject
import org.techtown.shoppingapp.databinding.ActivitySplashBinding
import org.techtown.shoppingapp.datas.BasicResponse
import org.techtown.shoppingapp.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : BaseActivity() {
    lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash)



        setupEvents()
        setValues()

    }

    override fun setupEvents() {

    }

    override fun setValues() {

        var isMyInfoLoad = false

        apiList.getRequestMyInfo(ContextUtil.getToken(mContext)).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful){
                    val br = response.body()!!

                    Log.d("yj","sp : ${br.message}")

                    isMyInfoLoad = true
                }else{
                    val jsonObj = JSONObject(response.errorBody()!!.string())
                    val br =jsonObj.getString("message")
                    Log.d("yj", "spLoginFail $br")
                }


            }
            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                Log.d("yj", "spFail ${t.message}")
            }
        })

        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed({

            val userAutoLogin = ContextUtil.getAutoLogin(mContext)

              val myIntent : Intent

              if(isMyInfoLoad && userAutoLogin ){
                  myIntent = Intent(mContext, MainActivity::class.java)

              } else {
                myIntent = Intent(mContext, MyInfoActivity::class.java)
                  startActivity(myIntent)
              }
            startActivity(myIntent)
            finish()



        }, 2500)

    }
}