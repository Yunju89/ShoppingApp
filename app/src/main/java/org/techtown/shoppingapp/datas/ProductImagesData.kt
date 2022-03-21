package org.techtown.shoppingapp.datas

import java.io.Serializable

class ProductImagesData(
    val id : Int,
    val index : Int,
    val product_id : Int,
    val image_url : String,
): Serializable {
}