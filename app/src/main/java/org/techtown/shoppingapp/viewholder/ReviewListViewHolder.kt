package org.techtown.shoppingapp.viewholder

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.shoppingapp.EditReviewActivity
import org.techtown.shoppingapp.MyReviewActivity
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.datas.OrderItems

class ReviewListViewHolder (parent:ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.my_review_list_item,parent,false)
) {
    val orderImg = itemView.findViewById<ImageView>(R.id.orderImg)
    val orderItem = itemView.findViewById<TextView>(R.id.orderItem)
    val orderOption = itemView.findViewById<TextView>(R.id.orderOption)
    val orderDate = itemView.findViewById<TextView>(R.id.orderDate)
    val ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)
    val reviewTitle = itemView.findViewById<TextView>(R.id.reviewTitle)
    val reviewContent = itemView.findViewById<TextView>(R.id.reviewContent)
    val reviewDate = itemView.findViewById<TextView>(R.id.reviewDate)
    val btnEditReview = itemView.findViewById<TextView>(R.id.btnEditReview)
    val btnDeleteReview = itemView.findViewById<TextView>(R.id.btnDeleteReview)


    fun bind( data : OrderItems) {

        Glide.with(itemView.context).load(data.product.product_main_images[0].image_url).into(orderImg)
        orderItem.text = data.product.name
        ratingBar.rating = data.review.score

        if (data.selected_options.isNotEmpty()){
            orderOption.isVisible = true

            var option = ""
            data.selected_options.forEach {
                if(option.isEmpty()){
                    option = it.option.name +"/"+ it.value.name
                }
                else {
                    option = option + "," + it.option.name +"/"+ it.value.name
                }
                orderOption.text = "[옵션] ${option}"
            }

        }else {
            orderOption.isVisible = false
        }

        orderDate.text = "주문일자 : ${data.product.created_at}"
        reviewTitle.text = data.review.review_title
        reviewContent.text = data.review.review_content
        reviewDate.text = data.review.created_at

        btnEditReview.setOnClickListener {
            val myIntent = Intent(itemView.context, EditReviewActivity::class.java)
            myIntent.putExtra("data2", data)
            (itemView.context as MyReviewActivity).startActivityForResult(myIntent, 0)
        }




    }


}