package org.techtown.shoppingapp.datas

class UserAllAddressData(
    val id : Int,
    val user_id : Int,
    val name : String,
    val phone : String,
    val zipcode : String,
    val address1 : String,
    val address2 : String,
    val is_basic_address : Boolean,
    val memo : String?,
    val created_at : String
) {

    fun getAddressMemo(): String {
        if(memo == null){
            return "[배송메모]"
        }
        return "[배송메모] $memo"
    }

}