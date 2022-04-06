package org.techtown.shoppingapp.datas

import java.io.Serializable

class ReviewsResponse(
    val id : Int,
    val user_id : Int,
    val review_title : String,
    val review_content : String,
    val score : Float,
    val review_images : ArrayList<ReviewImages>,
    val review_recommend : Int,
    val writer : WriterData,
    val created_at : String,
): Serializable {
}