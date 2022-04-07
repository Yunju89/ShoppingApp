package org.techtown.shoppingapp.interfaces

import org.techtown.shoppingapp.datas.CartResponse

interface CartItemDeletedListener {
    fun onDeletedItem(data: CartResponse)

    fun selectedCheckBox(data:CartResponse, isChecked:Boolean)
}