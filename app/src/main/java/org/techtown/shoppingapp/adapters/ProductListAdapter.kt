package org.techtown.shoppingapp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.shoppingapp.viewholder.ProductListViewHolder
import org.techtown.shoppingapp.datas.ProductsResponse

class ProductListAdapter(
    val mList : ArrayList<ProductsResponse>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductListViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is ProductListViewHolder -> {
                holder.bind(mList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }



}