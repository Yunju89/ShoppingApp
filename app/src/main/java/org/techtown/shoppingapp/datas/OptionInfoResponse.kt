package org.techtown.shoppingapp.datas

import java.io.Serializable

class OptionInfoResponse(
    val id : Int,
    val user_id : Int,
    val cart_id : Int,
    val option_id : Int,
    val value_id : Int,
    val option : OptionData,
    val value : ValueData,
) : Serializable {
}