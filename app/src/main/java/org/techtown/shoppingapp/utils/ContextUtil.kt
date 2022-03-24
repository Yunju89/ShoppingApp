package org.techtown.shoppingapp.utils

import android.content.Context

class ContextUtil {

    companion object{

        private val prefName = "ShoppingAppPref"

        private val LOGIN_USER_TOKEN = "LOGIN_USER_TOKEN"

        private val AUTO_LOGIN = "AUTO_LOGIN"

        fun setToken(context : Context, token : String){
            val pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
            pref.edit().putString(LOGIN_USER_TOKEN, token).apply()
        }

        fun getToken(context: Context) : String {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(LOGIN_USER_TOKEN, "")!!
        }

        fun setAutoLogin(context: Context, checked : Boolean){
            val pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
            pref.edit().putBoolean(AUTO_LOGIN, checked).apply()
        }

        fun getAutoLogin(context: Context) : Boolean {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getBoolean(AUTO_LOGIN, true)
        }



    }

}