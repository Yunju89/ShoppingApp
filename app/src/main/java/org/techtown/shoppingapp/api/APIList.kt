package org.techtown.shoppingapp.api

import org.techtown.shoppingapp.adapters.ProductReviewRecyclerAdapter
import org.techtown.shoppingapp.datas.BasicResponse
import org.techtown.shoppingapp.datas.ProductsResponse
import retrofit2.Call
import retrofit2.http.*

interface APIList {

    @FormUrlEncoded
    @PUT("/user")
    fun putRequestSignUp(
        @Field("email") email : String,
        @Field("password") password : String,
        @Field("name") name : String,
        @Field("phone") phone : String,
    ):Call<BasicResponse>

    @FormUrlEncoded
    @POST("/user")
    fun postRequestLogin(
        @Field("email") email: String,
        @Field("password") password: String,
    ):Call<BasicResponse>

    @GET("/user")
    fun getRequestMyInfo(
    ):Call<BasicResponse>

    @GET("/largecategory")
    fun getRequestLargeCategory():Call<BasicResponse>

    @GET("/smallcategory/{small_category_id}/product")
    fun getRequestProduct(
        @Path("small_category_id") small_category_id : Int,
        @Query("large_category_id") large_category_id : Int,
    ):Call<BasicResponse>

    @GET("/product/{product_id}")
    fun getRequestProductDetail(
        @Path("product_id") productId : Int,
    ):Call<BasicResponse>

    @GET("/cart")
    fun getRequestMyCart(
    ):Call<BasicResponse>

}