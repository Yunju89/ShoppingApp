package org.techtown.shoppingapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import org.techtown.shoppingapp.adapters.MainViewPagerAdapter
import org.techtown.shoppingapp.databinding.ActivityMainBinding
import org.techtown.shoppingapp.datas.BasicResponse
import org.techtown.shoppingapp.datas.LargeCategoriesData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMainBinding

    lateinit var mMainAdapter : MainViewPagerAdapter

    val mLargeCategoriesList = ArrayList<LargeCategoriesData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        setupEvents()
        setValues()



    }

    override fun setupEvents() {

        binding.imgCart.setOnClickListener {
            val myIntent = Intent(mContext, CartActivity::class.java)
            startActivity(myIntent)
        }

        binding.imgMyInfo.setOnClickListener {
            val myIntent = Intent(mContext, MyInfoActivity::class.java)
            startActivity(myIntent)
        }


    }

    override fun setValues() {

        apiList.getRequestLargeCategory().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if(response.isSuccessful){

                    mLargeCategoriesList.clear()

                    val br = response.body()!!

                    mLargeCategoriesList.addAll(br.data.large_categories)

                    mMainAdapter.notifyDataSetChanged()
                }


            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                Log.d("대분류", t.toString())
            }

        })



        mMainAdapter = MainViewPagerAdapter(supportFragmentManager, mLargeCategoriesList)
        binding.mainViewPager.adapter = mMainAdapter
        binding.mainViewPager.offscreenPageLimit = 3

        binding.mainTabLayout.setupWithViewPager(binding.mainViewPager)

    }

    var time : Long = 0
    override fun onBackPressed() {

        val time1 = System.currentTimeMillis()

        val time2 = time1 - time

        if(time2 in 0 .. 2000){
            finish()
        }
        else {
            time = time1
            Toast.makeText(mContext, "'뒤로' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }

    }


}