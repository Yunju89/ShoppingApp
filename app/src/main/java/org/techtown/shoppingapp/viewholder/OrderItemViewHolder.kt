package org.techtown.shoppingapp.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.datas.CartResponse
import java.text.DecimalFormat

class OrderItemViewHolder(parent : ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.viewholder_order_item, parent, false)
) {
    val orderImg = itemView.findViewById<ImageView>(R.id.orderImg)
    val orderItem = itemView.findViewById<TextView>(R.id.orderItem)
    val txtOption = itemView.findViewById<TextView>(R.id.txtOption)
    val txtOptionCount = itemView.findViewById<TextView>(R.id.txtOptionCount)
    val txtPrice = itemView.findViewById<TextView>(R.id.txtPrice)
    val view = itemView.findViewById<View>(R.id.view)

    fun bind(cartItem : CartResponse, isView : Boolean){

        Glide.with(itemView.context).load(cartItem.product_info.product_main_images[0].image_url).into(orderImg)
        orderItem.text = cartItem.product_info.name

        var option = ""
        cartItem.option_info.forEach {
            if(option.isEmpty()){
                option = it.option.name + "/" + it.value.name
            }
            else {
                option = option + "," + it.option.name + "/" + it.value.name
            }
        }
        txtOption.text = option
        txtOptionCount.text = "(${cartItem.quantity}개)"
        txtPrice.text = DecimalFormat("###,###").format(cartItem.quantity * cartItem.product_info.sale_price).toString()

        view.isVisible = isView

    }
}