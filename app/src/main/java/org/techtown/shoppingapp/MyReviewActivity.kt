package org.techtown.shoppingapp

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import org.json.JSONObject
import org.techtown.shoppingapp.adapters.ReviewListRecyclerAdapter
import org.techtown.shoppingapp.databinding.ActivityMyReviewBinding
import org.techtown.shoppingapp.datas.BasicResponse
import org.techtown.shoppingapp.datas.OrderItems
import org.techtown.shoppingapp.datas.OrderResponse
import org.techtown.shoppingapp.datas.UserReviewList
import org.techtown.shoppingapp.interfaces.ReviewDeletedListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyReviewActivity : BaseActivity(), ReviewDeletedListener {

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

        mReviewAdapter = ReviewListRecyclerAdapter(mReviewList, this)
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

    /**
     * 인터페이스 - 리뷰 목록 삭제
     */
    override fun onDeletedReview(id: Int) {

        Log.d("yj", "dataId : $id")

        AlertDialog.Builder(mContext)
            .setTitle("알림")
            .setMessage("삭제한 리뷰는 복구할 수 없습니다. 계속하시겠습니까?")
            .setPositiveButton("예", DialogInterface.OnClickListener { dialog, which ->
                apiList.deleteRequestReview(id).enqueue(object : Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if(response.isSuccessful){
                            Toast.makeText(mContext, "리뷰가 삭제 되었습니다.", Toast.LENGTH_SHORT).show()
                            getRequestReview()
                        }
                        else {
                            val jsonObj = JSONObject(response.errorBody()?.string())
                            val br = jsonObj.getString("message")
                            Log.d("yj", "delFail : $br")
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                        Log.d("yj", t.message.toString())
                        Toast.makeText(mContext, "잠시 후 다시 시도해 주세요.", Toast.LENGTH_SHORT).show()
                    }
                })
            })
            .setNegativeButton("아니오", null)
            .show()
    }
}