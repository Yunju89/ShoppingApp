package org.techtown.shoppingapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.datas.ProductsResponse

class SpinnerProductOptionAdapter(
    val mContext : Context,
    val resId : Int,
    val mList : ArrayList<ProductsResponse>
) : ArrayAdapter<ProductsResponse>(mContext,resId,mList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tmpRow = convertView
        if(tmpRow == null){
            tmpRow = LayoutInflater.from(mContext).inflate(R.layout.spinner_product_option, null)
        }

        val row = tmpRow!!

        val data = mList[position]
        val txtOption = row.findViewById<TextView>(R.id.txtOption)

        txtOption.text = data.product_options[position].option_values[position].name

        return row

    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tmpRow = convertView
        if(tmpRow == null){
            tmpRow = LayoutInflater.from(mContext).inflate(R.layout.spinner_product_option, null)
        }

        val row = tmpRow!!

        val data = mList[position]
        val txtOption = row.findViewById<TextView>(R.id.txtOption)

        txtOption.text = data.product_options[position].option_values[position].name

        return row

    }
}