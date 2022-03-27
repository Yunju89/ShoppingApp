package org.techtown.shoppingapp.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.adapters.SpinnerCartCountAdapter
import org.techtown.shoppingapp.datas.CartResponse
import org.w3c.dom.Text
import java.text.DecimalFormat

class CartListViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
) {

    val txtProductName = itemView.findViewById<TextView>(R.id.txtProductName)
    val imgCart = itemView.findViewById<ImageView>(R.id.imgCart)
    val btnExit = itemView.findViewById<ImageView>(R.id.btnExit)
    val checkBox = itemView.findViewById<CheckBox>(R.id.checkBox)
    val spinner = itemView.findViewById<Spinner>(R.id.countSpinner)
    val layoutOption = itemView.findViewById<LinearLayout>(R.id.layoutOption)
    val totalPrice = itemView.findViewById<TextView>(R.id.totalPrice)

    val spinnerArr = itemView.resources.getStringArray(R.array.arrCount)    // xml 에 만든 String 배열 가져옴

    fun bind(data: CartResponse) {

        val myFormat = DecimalFormat("###,###")

        txtProductName.text = data.product_info.name
        Glide.with(itemView.context).load(data.product_info.product_main_images[0].image_url).into(imgCart)

        itemView.findViewById<Spinner>(R.id.countSpinner).adapter = SpinnerCartCountAdapter(itemView.context, R.id.spinnerSelected, spinnerArr) //spinnerArr 타입이 array

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectCount = spinnerArr[position]
                totalPrice.text = myFormat.format(data.product_info.sale_price*(selectCount.toInt())).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        data.option_info.forEach {
            val view = LayoutInflater.from(itemView.context).inflate(R.layout.cart_option_info,null,false)
            view.findViewById<TextView>(R.id.txtOption).text = it.option.name
            view.findViewById<TextView>(R.id.txtValue).text = it.value.name
            layoutOption.addView(view)

        }





    }

}