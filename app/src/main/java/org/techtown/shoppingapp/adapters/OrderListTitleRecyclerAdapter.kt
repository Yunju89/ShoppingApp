package org.techtown.shoppingapp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.shoppingapp.datas.OrderResponse
import org.techtown.shoppingapp.viewholder.OrderListTitleViewHolder

class OrderListTitleRecyclerAdapter(
    val mList : ArrayList<OrderResponse>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return OrderListTitleViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is OrderListTitleViewHolder -> {
                holder.bind(mList[position])
            }
        }

    }

    override fun getItemCount() = mList.size
}