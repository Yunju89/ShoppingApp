package org.techtown.shoppingapp.viewholder

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.shoppingapp.EditReviewActivity
import org.techtown.shoppingapp.MyOrderActivity
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.RegisterReviewActivity
import org.techtown.shoppingapp.datas.OrderItems
import java.text.DecimalFormat


class OrderListItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.my_order_list_item, parent, false)
) {

    val orderImg = itemView.findViewById<ImageView>(R.id.orderImg)
    val orderItem = itemView.findViewById<TextView>(R.id.orderItem)
    val orderOption = itemView.findViewById<TextView>(R.id.orderOption)
    val orderPriceCount = itemView.findViewById<TextView>(R.id.orderPriceCount)
    val btnReview = itemView.findViewById<TextView>(R.id.btnReview)

    fun bind(data: OrderItems) {

        Glide.with(itemView.context).load(data.product.product_main_images[0].image_url)
            .into(orderImg)
        orderItem.text = data.product.name

        if (data.selected_options.isNotEmpty()) {
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
        } else {
            orderOption.isVisible = false
        }

        val myFormat = DecimalFormat("###,###")

        orderPriceCount.text =
            "${myFormat.format(data.product.sale_price * data.product_quantity)} / ${data.product_quantity}개"


        var review = false

        if (data.review == null) {
            btnReview.text = "리뷰등록"
            btnReview.setTextColor(Color.parseColor("#FF3700B3"))
            review = true

        } else {
            btnReview.text = "리뷰수정"
            review = false
        }

        btnReview.setOnClickListener {

            if (review) {
                val myIntent = Intent(itemView.context, RegisterReviewActivity::class.java)
                myIntent.putExtra("data", data)
                (itemView.context as MyOrderActivity).startActivityForResult(myIntent, 0)
            } else {
                val myIntent = Intent(itemView.context, EditReviewActivity::class.java)
                myIntent.putExtra("data2", data)
                (itemView.context as MyOrderActivity).startActivityForResult(myIntent, 0)
            }

        }


    }
}