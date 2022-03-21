package org.techtown.shoppingapp.datas

import java.io.Serializable

class SmallCategoriesData(
    val id : Int,
    val name : String,
    val large_category_id : Int,
) : Serializable {
}