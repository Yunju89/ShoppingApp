package org.techtown.shoppingapp.adapters.viewholder

import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.shoppingapp.ProductDetailActivity
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.datas.ProductsResponse
import java.text.DecimalFormat

class ProductListViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.product_list_item, parent,false)
) {

    val listImage = itemView.findViewById<ImageView>(R.id.listImage)
    val txtPriceName = itemView.findViewById<TextView>(R.id.txtPriceName)
    val txtSalePrice = itemView.findViewById<TextView>(R.id.txtSalePrice)
    val txtOriginalPrice = itemView.findViewById<TextView>(R.id.txtOriginalPrice)

    fun bind(data : ProductsResponse) {
        val myFormat = DecimalFormat("###,###")

        Glide.with(itemView.context).load(data.product_main_images[0].image_url).into(listImage)
        txtPriceName.text = data.name
        txtSalePrice.text = myFormat.format(data.sale_price).toString()
        txtOriginalPrice.text = myFormat.format(data.original_price).toString()
        txtOriginalPrice.paintFlags = txtOriginalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG


        itemView.setOnClickListener {
            val myIntent = Intent(itemView.context, ProductDetailActivity::class.java)
            itemView.context.startActivity(myIntent)
        }

    }

}