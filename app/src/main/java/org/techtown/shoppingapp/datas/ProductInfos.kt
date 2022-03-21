package org.techtown.shoppingapp.datas

import java.io.Serializable

class ProductInfos(
    val id : Int,
    val product_id : Int,
    val description : String,
    val description_content : String,
) : Serializable {
}