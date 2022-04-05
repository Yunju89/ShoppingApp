package org.techtown.shoppingapp.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.datas.OrderResponse


class OrderListViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.my_order_list_item,parent,false)
) {
    fun bind (data : OrderResponse) {

    }
}