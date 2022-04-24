package org.techtown.shoppingapp.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.datas.CartResponse
import java.text.DecimalFormat

class PaymentPriceViewHolder(parent : ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.viewholder_payment_price, parent, false)
) {
    val totalPrice = itemView.findViewById<TextView>(R.id.totalPrice)
    val shippingFee = itemView.findViewById<TextView>(R.id.shippingFee)
    val allPrice = itemView.findViewById<TextView>(R.id.allPrice)

    fun bind(cartList : ArrayList<CartResponse>){

        val myFormat = DecimalFormat("###,###")

        var priceTotal = 0
        var priceShippingFee = 0


        cartList.forEach {
            val salePrice = it.product_info.sale_price
            val quantity = it.quantity
            val price = salePrice*quantity

            priceTotal += price

        }

        totalPrice.text = myFormat.format(priceTotal).toString()

        if(priceTotal >= 30000){
            priceShippingFee = 0
        }else{
            priceShippingFee = 3000
        }

        shippingFee.text = myFormat.format(priceShippingFee).toString()

        allPrice.text = myFormat.format(priceTotal+priceShippingFee).toString()

    }
}