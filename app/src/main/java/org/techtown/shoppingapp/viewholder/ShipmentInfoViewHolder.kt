package org.techtown.shoppingapp.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.adapters.SpinnerShippingRequestAdapter
import org.techtown.shoppingapp.datas.DataResponse
import org.techtown.shoppingapp.interfaces.ShipmentInfoListener

class ShipmentInfoViewHolder(parent : ViewGroup, val listener: ShipmentInfoListener) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.viewholder_shipment_info, parent, false)
) {

    val txtName = itemView.findViewById<TextView>(R.id.txtName)
    val txtAddress1 = itemView.findViewById<TextView>(R.id.txtAddress1)
    val txtAddress2 = itemView.findViewById<TextView>(R.id.txtAddress2)
    val txtPhoneNum = itemView.findViewById<TextView>(R.id.txtPhoneNum)

    val btnChangeAddress = itemView.findViewById<TextView>(R.id.btnChangeAddress)
    val spinnerShipment = itemView.findViewById<Spinner>(R.id.spinnerShipment)
    val edtShipment = itemView.findViewById<EditText>(R.id.edtShipment)

    val spinnerArr = itemView.resources.getStringArray(R.array.arrShipping)     // spinner 담을 arr 가져와서

    val shipmentLayout = itemView.findViewById<LinearLayout>(R.id.shipmentLayout)
    val newShipmentLayout = itemView.findViewById<LinearLayout>(R.id.newShipmentLayout)

    val btnNewShipment = itemView.findViewById<TextView>(R.id.btnNewShipment)

    fun bind(shipmentData : DataResponse?){

        if(shipmentData==null){
            shipmentLayout.isVisible = false
            newShipmentLayout.isVisible = true

            btnNewShipment.setOnClickListener {
                listener.onClickShipmentInfo()
            }
        }
        else{

            shipmentLayout.isVisible = true
            newShipmentLayout.isVisible = false

            txtName.text = shipmentData.basic_address.name
            txtAddress1.text = shipmentData.basic_address.address1
            txtAddress2.text = shipmentData.basic_address.address2
            txtPhoneNum.text = shipmentData.basic_address.phone

            spinnerShipment.adapter = SpinnerShippingRequestAdapter(itemView.context, R.layout.spinner_shipping_request, spinnerArr)     // 연결

            spinnerShipment.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    edtShipment.isVisible = position==5

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }


            }

            btnChangeAddress.setOnClickListener {
                listener.onClickShipmentInfo()
            }
        }



    }


}