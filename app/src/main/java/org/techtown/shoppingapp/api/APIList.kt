package org.techtown.shoppingapp.api

import org.techtown.shoppingapp.datas.BasicResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIList {

    @GET("/largecategory")
    fun getRequestLargeCategory():Call<BasicResponse>

    @GET("/smallcategory/{small_category_id}/product")
    fun getRequestProduct(
        @Path("small_category_id") small_category_id : Int,
        @Query("large_category_id") large_category_id : Int,

    ):Call<BasicResponse>

}