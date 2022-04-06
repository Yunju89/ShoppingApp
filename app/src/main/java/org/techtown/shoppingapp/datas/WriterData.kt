package org.techtown.shoppingapp.datas

import java.io.Serializable

class WriterData(
    val id : Int,
    val email : String,
    val name : String,
    val phone : String,
    val is_admin : Boolean,
    val profile_img_url : String,
    val created_at : String,
    val retired_at : Int,
): Serializable {
}