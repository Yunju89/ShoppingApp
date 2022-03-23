package org.techtown.shoppingapp.datas

class ReviewsResponse(
    val id : Int,
    val user_id : Int,
    val review_title : String,
    val review_content : String,
    val score : Int,
    val review_images : ArrayList<ReviewImages>,
    val review_recommend : Int,
    val writer : ArrayList<WriterData>,
    val created_at : String,
) {
}