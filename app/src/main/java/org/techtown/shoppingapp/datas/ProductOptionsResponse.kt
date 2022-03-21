package org.techtown.shoppingapp.datas

import java.io.Serializable

class ProductOptionsResponse(
    val id : Int,
    val name : String,
    val product_id : Int,
    val option_values : ArrayList<OptionValuesData>,
): Serializable {
}