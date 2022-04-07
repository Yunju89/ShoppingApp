package org.techtown.shoppingapp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.shoppingapp.datas.OrderItems
import org.techtown.shoppingapp.interfaces.ReviewDeletedListener
import org.techtown.shoppingapp.viewholder.ReviewListViewHolder

class ReviewListRecyclerAdapter(
    val mReviewList : ArrayList<OrderItems>,
    val listener : ReviewDeletedListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ReviewListViewHolder(parent, listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ReviewListViewHolder -> {
                holder.bind(mReviewList[position])
            }
        }
    }

    override fun getItemCount() = mReviewList.size

}