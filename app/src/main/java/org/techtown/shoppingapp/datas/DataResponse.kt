package org.techtown.shoppingapp.datas

class DataResponse(

    val large_categories : ArrayList<LargeCategoriesData>,

    val products : ArrayList<ProductsResponse>,

    val product : ProductsResponse,

    val user : UserData,

    val token : String,

    val carts : ArrayList<CartResponse>,

    val order : ArrayList<OrderResponse>,

    val user_review_list : ArrayList<OrderItems>

    ) {

    /**
     * filter -> { 내부 (true) 것만 남겨라 }
     * toList -> 남긴걸 다시  List 형태로 만들어라.
     */

    fun getOrderList(): List<OrderResponse> {
        return order.filter {
            it.order_items.size > 0
        }.toList()
    }
}