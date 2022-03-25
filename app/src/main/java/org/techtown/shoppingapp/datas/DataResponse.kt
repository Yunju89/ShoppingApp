package org.techtown.shoppingapp.datas

class DataResponse(

    val large_categories : ArrayList<LargeCategoriesData>,

    val products : ArrayList<ProductsResponse>,

    val product : ProductsResponse,

    val user : UserData,

    val token : String,

    val carts : ArrayList<CartResponse>,

    ) {


}