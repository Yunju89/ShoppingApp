package org.techtown.shoppingapp.`interface`

import org.techtown.shoppingapp.datas.CartResponse

interface CartItemDeletedListener {
    fun onDeletedItem(data: CartResponse)

    fun selectedCheckBox(data:CartResponse, isChecked:Boolean)
}