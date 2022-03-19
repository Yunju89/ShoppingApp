package org.techtown.shoppingapp.datas

class ProductOptionsResponse(
    val id : Int,
    val name : String,
    val product_id : Int,
    val option_values : ArrayList<OptionValuesData>,
) {
}