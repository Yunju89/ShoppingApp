package org.techtown.shoppingapp.api

import org.techtown.shoppingapp.datas.BasicResponse
import retrofit2.Call
import retrofit2.http.GET

interface APIList {

    @GET("/largecategory")
    fun getRequestLargeCategory():Call<BasicResponse>

}