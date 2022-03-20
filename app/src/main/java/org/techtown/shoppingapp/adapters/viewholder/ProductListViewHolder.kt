package org.techtown.shoppingapp.adapters.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.datas.ProductsResponse

class ProductListViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.product_list_item, parent,false)
) {

    val listImage = itemView.findViewById<ImageView>(R.id.listImage)
    val txtPriceName = itemView.findViewById<TextView>(R.id.txtPriceName)
    val txtSalePrice = itemView.findViewById<TextView>(R.id.txtSalePrice)
    val txtOriginalPrice = itemView.findViewById<TextView>(R.id.txtOriginalPrice)

    fun bind(data : ProductsResponse) {

        Glide.with(itemView.context).load(data.product_main_images[0].image_url).into(listImage)
        txtPriceName.text = data.name
        txtSalePrice.text = data.sale_price.toString()
        txtOriginalPrice.text = data.original_price.toString()

    }
}