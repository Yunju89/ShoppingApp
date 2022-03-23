package org.techtown.shoppingapp.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.datas.ReviewsResponse

class ProductReviewViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.product_review_item, parent, false)
) {
    val writerName = itemView.findViewById<TextView>(R.id.writerName)
    val ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)
    val reviewCreateAt = itemView.findViewById<TextView>(R.id.reviewCreateAt)
    val reviewTitle = itemView.findViewById<TextView>(R.id.reviewTitle)
    val reviewContent = itemView.findViewById<TextView>(R.id.reviewContent)

    fun bind(data: ReviewsResponse) {

        writerName.text = data.writer[0].name
        ratingBar.rating = data.score.toFloat()
        reviewCreateAt.text = data.created_at
        reviewTitle.text = data.review_title
        reviewContent.text = data.review_content
    }
}