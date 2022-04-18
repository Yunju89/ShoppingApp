package org.techtown.shoppingapp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.shoppingapp.datas.UserAllAddressData
import org.techtown.shoppingapp.viewholder.ShipmentListViewHolder

class ShipmentListAdapter(
    val mList : ArrayList<UserAllAddressData>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ShipmentListViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ShipmentListViewHolder -> {
                holder.bind(mList[position])
            }
        }
    }

    override fun getItemCount() = mList.size
}