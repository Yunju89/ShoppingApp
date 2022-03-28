package org.techtown.shoppingapp.datas

class CartResponse(

    val id : Int,
    val product_id : Int,
    val user_id : Int,
    val quantity : Int,
    val product_info : ProductsResponse,
    val option_info : ArrayList<OptionInfoResponse>,
    ) {
}