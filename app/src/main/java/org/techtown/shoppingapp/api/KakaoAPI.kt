package org.techtown.shoppingapp.api

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class KakaoAPI {
    companion object {

        private var retrofit: Retrofit? = null      // null 상태로 만듦. (싱글톤 패턴 사용 방법 공식처럼 '싱글톤 사용법 찾아보기')

        private val BASE_URL = "https://dapi.kakao.com"

        private val API_KEY = "KakaoAK 5dbf7e9fefccf42f7b8608138682024b"


        fun getKakaoRetrofit () : Retrofit {

            if(retrofit ==null){

                val interceptor = Interceptor{
                    with(it) {

                        val newRequest = request().newBuilder()
                            .addHeader("Authorization", API_KEY)
                            .build()

                        proceed(newRequest)
                    }
                }

                val myClient = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(myClient)
                    .build()

            }

            return retrofit!!
        }


    }

}