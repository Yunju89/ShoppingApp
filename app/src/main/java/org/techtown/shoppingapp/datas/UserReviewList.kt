package org.techtown.shoppingapp.datas

import java.io.Serializable

class UserReviewList(
    val id : Int,
    val merchant_uid : String,
    val product_id : Int,
    val product_quantity : Int,
    val product : ProductsResponse,
    val review : ReviewsResponse,
    val selected_options : ArrayList<SelectedOptions>,
):Serializable {
}