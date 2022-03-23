package org.techtown.shoppingapp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.shoppingapp.datas.ReviewsResponse
import org.techtown.shoppingapp.viewholder.ProductReviewViewHolder

class ProductReviewRecyclerAdapter(
    val mList: ArrayList<ReviewsResponse>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductReviewViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProductReviewViewHolder -> {
                holder.bind(mList[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }
}