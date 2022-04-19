package org.techtown.shoppingapp.kakaodatas

data class Document(
    val address: Address,
    val address_name: String,
    val address_type: String,
    val road_address: RoadAddress?,
    val x: String,
    val y: String
){

    fun isRoadAddress(): Boolean {
        if (road_address == null) {
            return false
        }
        return true
    }

}