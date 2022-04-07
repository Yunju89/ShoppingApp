package org.techtown.shoppingapp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.shoppingapp.datas.CartResponse
import org.techtown.shoppingapp.viewholder.*

class PaymentRecyclerAdapter(
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mList : ArrayList<Int> = arrayListOf() // viewTypeList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            0 -> ShipmentInfoViewHolder(parent)
            1 -> OrderTitleViewHolder(parent)
            3 -> PaymentPriceViewHolder(parent)
            4 -> PaymentViewHolder(parent)
            else -> OrderItemViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount() = mList.size

    override fun getItemViewType(position: Int): Int {
        return mList[position]
    }

    fun setData(cartList : ArrayList<CartResponse>) {
        mList.clear()
        mList.add(0) //배송지 viewType
        mList.add(1) //주문상품 타이틀 viewType
        cartList.forEach {
            mList.add(2) // 주문 상품 목록 viewType
        }
        mList.add(3) // 주문 가격 viewType
        mList.add(4) // 결제 수단 viewType
    }
}