package org.techtown.shoppingapp.datas

class OrderResponse(
    val id : Int,
    val user_id : Int,
    val receiver_name : String,
    val address : String,
    val number_zip : String,
    val phone_num : String,
    val request_message : String,
    val payment_uid : String,
    val merchant_uid : String,
    val payment_money : Int,
    val created_at : String,
    val user : UserData,
    val order_items : ArrayList<OrderItems>

) {
}