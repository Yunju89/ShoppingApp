package org.techtown.shoppingapp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.shoppingapp.`interface`.CartItemDeletedListener
import org.techtown.shoppingapp.datas.CartResponse
import org.techtown.shoppingapp.viewholder.CartListViewHolder

class CartListRecyclerAdapter(
    val mList : ArrayList<CartResponse>,
    val listener : CartItemDeletedListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CartListViewHolder(parent, listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is CartListViewHolder -> {
                holder.bind(mList[position])
            }
        }
    }

    override fun getItemCount() = mList.size
}