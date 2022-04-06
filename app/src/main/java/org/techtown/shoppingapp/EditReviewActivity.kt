package org.techtown.shoppingapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import org.techtown.shoppingapp.databinding.ActivityEditReviewBinding
import org.techtown.shoppingapp.datas.BasicResponse
import org.techtown.shoppingapp.datas.OrderItems
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditReviewActivity : BaseActivity() {
    lateinit var binding : ActivityEditReviewBinding
    lateinit var orderItem : OrderItems

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_review)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnSave.setOnClickListener {
            val reviewId = orderItem.review.id
            val inputTitle = binding.edtReviewTitle.text.toString()
            val inputReview = binding.edtReviewDetail.text.toString()
            val inputScore = binding.ratingBar.rating

            apiList.putRequestEditReview(reviewId,inputTitle,inputReview,inputScore).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                   if (response.isSuccessful){
                       Toast.makeText(mContext, "리뷰 수정이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                       setResult(RESULT_OK)
                       finish()
                   }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                    Log.d("리뷰수정", t.message.toString())
                }

            })

        }

        binding.btnExit.setOnClickListener {
            alertDialog()
        }

    }

    override fun setValues() {

        orderItem = intent.getSerializableExtra("data2") as OrderItems


        Glide.with(mContext).load(orderItem.product.product_main_images[0].image_url).into(binding.productImg)
        binding.productName.text = orderItem.product.name

        binding.ratingBar.rating = orderItem.review.score
        binding.edtReviewTitle.setText(orderItem.review.review_title)
        binding.edtReviewDetail.setText(orderItem.review.review_content)


    }

    override fun onBackPressed() {
        alertDialog()
    }

    fun alertDialog(){

        AlertDialog.Builder(mContext)
            .setTitle("취소하시겠습니까?")
            .setMessage("취소하면 변경하신 사항이 저장되지 않습니다.")
            .setPositiveButton("예", DialogInterface.OnClickListener { dialog, which ->
                finish()
            })
            .setNegativeButton("아니오", null)
            .show()

    }

}