package org.techtown.shoppingapp.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.datas.UserAllAddressData
import org.techtown.shoppingapp.interfaces.SelectShipmentInfoListener
import org.techtown.shoppingapp.interfaces.ShipmentDeletedListener

class ShipmentListViewHolder(parent : ViewGroup, val listener : ShipmentDeletedListener, val selectListener : SelectShipmentInfoListener) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(
    R.layout.my_shipment_list_item,parent,false)) {

    val txtName = itemView.findViewById<TextView>(R.id.txtName)
    val txtBaseAddress = itemView.findViewById<TextView>(R.id.txtBaseAddress)
    val txtAddress1 = itemView.findViewById<TextView>(R.id.txtAddress1)
    val txtAddress2 = itemView.findViewById<TextView>(R.id.txtAddress2)
    val txtPhoneNum = itemView.findViewById<TextView>(R.id.txtPhoneNum)
    val txtMemo = itemView.findViewById<TextView>(R.id.txtMemo)
    val btnDeleteShipment = itemView.findViewById<TextView>(R.id.btnDeleteShipment)
    val shipmentListLayout = itemView.findViewById<LinearLayout>(R.id.shipmentListLayout)



    fun bind(list : UserAllAddressData){

        txtName.text = list.name

        if(list.is_basic_address){
            txtBaseAddress.isVisible = true
        }

        txtAddress1.text = list.address1
        txtAddress2.text = list.address2
        txtPhoneNum.text = list.phone
        txtMemo.text = list.getAddressMemo()

        btnDeleteShipment.setOnClickListener {
            listener.onDeletedShipment(list.id)
        }

        shipmentListLayout.setOnClickListener {
            selectListener.onSelectedShipmentList(list)
        }


    }
}