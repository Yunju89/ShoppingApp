package org.techtown.shoppingapp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.shoppingapp.datas.OrderItems
import org.techtown.shoppingapp.viewholder.OrderListItemViewHolder

class OrderListItemRecyclerAdapter(
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val list : ArrayList<OrderItems> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return OrderListItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is OrderListItemViewHolder -> {
                holder.bind(list[position])
            }
        }
    }

    override fun getItemCount() = list.size

    fun setData(mList : ArrayList<OrderItems>) {
        list.clear()
        list.addAll(mList)
        notifyDataSetChanged()
    }
}