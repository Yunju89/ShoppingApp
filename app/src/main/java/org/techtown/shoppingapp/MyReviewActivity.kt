package org.techtown.shoppingapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.shoppingapp.adapters.ReviewListRecyclerAdapter
import org.techtown.shoppingapp.databinding.ActivityMyReviewBinding
import org.techtown.shoppingapp.datas.BasicResponse
import org.techtown.shoppingapp.datas.OrderItems
import org.techtown.shoppingapp.datas.OrderResponse
import org.techtown.shoppingapp.datas.UserReviewList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyReviewActivity : BaseActivity() {

    lateinit var binding : ActivityMyReviewBinding
    var mReviewList = ArrayList<OrderItems>()
    lateinit var  mReviewAdapter : ReviewListRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_review)

        setupEvents()
        getRequestReview()

    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mReviewAdapter = ReviewListRecyclerAdapter(mReviewList)
        binding.reviewRecyclerView.adapter = mReviewAdapter
        binding.reviewRecyclerView.layoutManager = LinearLayoutManager(mContext)


    }


    private fun getRequestReview(){
        apiList.getRequestReview("작성완료").enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        mReviewList = it.data.user_review_list

                        setValues()

                    }
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                Log.d("리뷰목록", t.message.toString())
            }

        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK){
            getRequestReview()
        }
    }
}