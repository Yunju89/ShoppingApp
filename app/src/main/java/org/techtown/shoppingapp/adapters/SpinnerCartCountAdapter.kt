package org.techtown.shoppingapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.datas.CartResponse
import org.techtown.shoppingapp.datas.OptionValuesData
import org.techtown.shoppingapp.datas.ProductOptionsResponse
import org.techtown.shoppingapp.datas.ProductsResponse

class SpinnerCartCountAdapter(
    val mContext : Context,
    val resId : Int,
    val mList : Array<String>
) : ArrayAdapter<String>(mContext,resId,mList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tmpRow = convertView
        if(tmpRow == null){
            tmpRow = LayoutInflater.from(mContext).inflate(R.layout.spinner_cart_count, null)
        }

        val row = tmpRow!!

        val data = mList[position]
        val cartCount = row.findViewById<TextView>(R.id.cartCount)

        cartCount.text = data

        return row

    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tmpRow = convertView
        if(tmpRow == null){
            tmpRow = LayoutInflater.from(mContext).inflate(R.layout.spinner_cart_count, null)
        }

        val row = tmpRow!!

        val data = mList[position]
        val cartCount = row.findViewById<TextView>(R.id.cartCount)

        cartCount.text = data

        return row

    }
}