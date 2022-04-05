package org.techtown.shoppingapp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.shoppingapp.datas.OrderResponse
import org.techtown.shoppingapp.viewholder.OrderListViewHolder

class OrderListRecyclerAdapter(
    val mList : ArrayList<OrderResponse>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return OrderListViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is OrderListViewHolder -> {
                holder.bind(mList[position])
            }
        }
    }

    override fun getItemCount() = mList.size
}