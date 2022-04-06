package org.techtown.shoppingapp.viewholder

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.adapters.OrderListItemRecyclerAdapter
import org.techtown.shoppingapp.datas.OrderResponse

class OrderListTitleViewHolder(parent : ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.my_order_list_title,parent,false)
) {

    val txtDate = itemView.findViewById<TextView>(R.id.txtDate)
    val orderRecyclerView = itemView.findViewById<RecyclerView>(R.id.orderRecyclerView)

    var OrderListAdapter = OrderListItemRecyclerAdapter()


    init {      // onCreate 처럼 딱 한번 생성
        orderRecyclerView.adapter = OrderListAdapter
        orderRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
    }

    fun bind(data : OrderResponse){

        txtDate.text = data.created_at
        OrderListAdapter.setData(data.order_items)
    }


}