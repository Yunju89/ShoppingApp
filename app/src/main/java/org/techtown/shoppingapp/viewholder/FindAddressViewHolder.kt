package org.techtown.shoppingapp.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.interfaces.FindZipcodeListener
import org.techtown.shoppingapp.kakaodatas.Document

class FindAddressViewHolder(parent: ViewGroup, val zipcodeListener : FindZipcodeListener) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.find_address_list_item, parent, false)
) {
    val txtZipCode = itemView.findViewById<TextView>(R.id.txtZipCode)
    val txtLoadAddress = itemView.findViewById<TextView>(R.id.txtLoadAddress)
    val txtAddress = itemView.findViewById<TextView>(R.id.txtAddress)

    fun bind(data: Document) {


        txtZipCode.text = data.road_address?.zone_no
        txtLoadAddress.text = data.road_address?.address_name
        txtAddress.text = data.address.address_name

        val zipCode = data.road_address?.zone_no.toString()
        val roadAddress = data.road_address?.address_name.toString()
        val address = data.address.address_name


        txtLoadAddress.setOnClickListener {
            zipcodeListener.onFindShipment(roadAddress,zipCode)
        }

        txtAddress.setOnClickListener {
            zipcodeListener.onFindShipment(address,zipCode)
        }

    }

}