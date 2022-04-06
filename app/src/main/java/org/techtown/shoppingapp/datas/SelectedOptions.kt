package org.techtown.shoppingapp.datas

import java.io.Serializable

class SelectedOptions(
    val id : Int,
    val order_id : Int,
    val order_item_id : Int,
    val option_id : Int,
    val value_id : Int,
    val option : OptionData,
    val value : ValueData
): Serializable {
}