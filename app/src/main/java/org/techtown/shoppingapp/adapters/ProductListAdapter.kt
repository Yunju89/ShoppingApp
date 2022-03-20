package org.techtown.shoppingapp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.shoppingapp.adapters.viewholder.ProductListViewHolder

class ProductListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductListViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 0
    }



}