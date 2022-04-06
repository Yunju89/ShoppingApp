package org.techtown.shoppingapp.viewholder

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.RegistReviewActivity
import org.techtown.shoppingapp.datas.OrderItems
import org.techtown.shoppingapp.datas.OrderResponse
import java.text.DecimalFormat


class OrderListItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.my_order_list_item, parent, false)
) {

    val orderImg = itemView.findViewById<ImageView>(R.id.orderImg)
    val orderItem = itemView.findViewById<TextView>(R.id.orderItem)
    val orderOption = itemView.findViewById<TextView>(R.id.orderOption)
    val orderPriceCount = itemView.findViewById<TextView>(R.id.orderPriceCount)
    val btnReview = itemView.findViewById<Button>(R.id.btnReview)

    fun bind(data: OrderItems) {

        Glide.with(itemView.context).load(data.product.product_main_images[0].image_url).into(orderImg)
        orderItem.text = data.product.name

        if (data.selected_options.isNotEmpty()){
            orderOption.isVisible = true

            var option = ""
            data.selected_options.forEach {
                option = if (option.isEmpty()) {
                    it.option.name + "/" + it.value.name
                } else {
                    "$option , " + it.option.name + "/" + it.value.name
                }
            }

            orderOption.text = option
        }
        else {
            orderOption.isVisible = false
        }

        val myFormat = DecimalFormat("###,###")

        orderPriceCount.text = "${myFormat.format(data.product.sale_price*data.product_quantity)} / ${data.product_quantity}개"

        btnReview.setOnClickListener {
            val myIntent = Intent(itemView.context, RegistReviewActivity::class.java)
            itemView.context.startActivity(myIntent)
        }

    }
}