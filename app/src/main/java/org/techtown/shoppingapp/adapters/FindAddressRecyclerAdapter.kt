package org.techtown.shoppingapp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.shoppingapp.interfaces.FindZipcodeListener
import org.techtown.shoppingapp.kakaodatas.Document
import org.techtown.shoppingapp.viewholder.FindAddressViewHolder

class FindAddressRecyclerAdapter(
    val mList : List<Document>,
    val zipcodeListener : FindZipcodeListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FindAddressViewHolder(parent, zipcodeListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is FindAddressViewHolder -> {
                holder.bind(mList[position])
            }
        }
    }

    override fun getItemCount() = mList.size
}