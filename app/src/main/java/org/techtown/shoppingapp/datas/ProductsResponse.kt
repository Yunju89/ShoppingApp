package org.techtown.shoppingapp.datas

import java.io.Serializable

class ProductsResponse (
    val id : Int,
    val large_category_info : LargeCategoriesData,
    val small_category_info : SmallCategoriesData,
    val name : String,
    val original_price : Int,
    val sale_price : Int,
    val product_detail_images : ArrayList<ProductImagesData>,
    val product_infos : ArrayList<ProductInfos>,
    val created_at : String,
    val product_options : ArrayList<ProductOptionsResponse>,
    val reviews : ArrayList<ReviewsResponse>,
    val product_main_images : ArrayList<ProductImagesData>,

    ): Serializable{
}