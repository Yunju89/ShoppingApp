package org.techtown.shoppingapp.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.datas.UserAllAddressData
import org.techtown.shoppingapp.interfaces.ShipmentChangedListener

class ShipmentListViewHolder(parent : ViewGroup, val listener : ShipmentChangedListener) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(
    R.layout.my_shipment_list_item,parent,false)) {

    val txtName = itemView.findViewById<TextView>(R.id.txtName)
    val txtBaseAddress = itemView.findViewById<TextView>(R.id.txtBaseAddress)
    val txtAddress1 = itemView.findViewById<TextView>(R.id.txtAddress1)
    val txtAddress2 = itemView.findViewById<TextView>(R.id.txtAddress2)
    val txtPhoneNum = itemView.findViewById<TextView>(R.id.txtPhoneNum)
    val txtMemo = itemView.findViewById<TextView>(R.id.txtMemo)
    val btnDeleteShipment = itemView.findViewById<TextView>(R.id.btnDeleteShipment)



    fun bind(list : UserAllAddressData){

        txtName.text = list.name

        if(list.is_basic_address){
            txtBaseAddress.isVisible = true
        }

        txtAddress1.text = list.address1
        txtAddress2.text = list.address2
        txtPhoneNum.text = list.phone
        txtMemo.text = "[배송메모] ${list.memo}"

        btnDeleteShipment.setOnClickListener {
            listener.onChangedShipment(list.id)
        }

    }
}