package org.techtown.shoppingapp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.shoppingapp.datas.UserAllAddressData
import org.techtown.shoppingapp.interfaces.SelectShipmentInfoListener
import org.techtown.shoppingapp.interfaces.ShipmentChangedListener
import org.techtown.shoppingapp.viewholder.ShipmentListViewHolder

class ShipmentListAdapter(
    val mList : ArrayList<UserAllAddressData>,
    val listener : ShipmentChangedListener,
    val selectListener : SelectShipmentInfoListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ShipmentListViewHolder(parent, listener, selectListener)
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