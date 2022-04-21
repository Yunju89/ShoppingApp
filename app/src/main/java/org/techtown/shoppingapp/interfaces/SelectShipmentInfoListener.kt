package org.techtown.shoppingapp.interfaces

import org.techtown.shoppingapp.datas.DataResponse
import org.techtown.shoppingapp.datas.UserAllAddressData

interface SelectShipmentInfoListener {

    fun onSelectedShipmentList(shipmentData : UserAllAddressData)

}