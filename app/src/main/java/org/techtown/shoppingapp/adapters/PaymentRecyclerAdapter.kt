package org.techtown.shoppingapp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.shoppingapp.datas.CartResponse
import org.techtown.shoppingapp.datas.DataResponse
import org.techtown.shoppingapp.interfaces.ShipmentInfoListener
import org.techtown.shoppingapp.viewholder.*

class PaymentRecyclerAdapter(
    val shipmentData : DataResponse,
    val cartList : ArrayList<CartResponse>,
    val listener: ShipmentInfoListener

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mList : ArrayList<Int> = arrayListOf() // viewTypeList

    init {
        setData()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            0 -> ShipmentInfoViewHolder(parent, listener)
            1 -> OrderTitleViewHolder(parent)
            3 -> PaymentPriceViewHolder(parent)
            4 -> PaymentViewHolder(parent)
            else -> OrderItemViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ShipmentInfoViewHolder -> {
                holder.bind(shipmentData)
            }
            is OrderItemViewHolder -> {

                val cartPosition = position-2       // cartList, position 달라서 (getCount 를 mList 기준으로 했기때문에, mList 와 cartList 순서를 맞춰줌.)
                if (cartPosition >=0 && cartPosition<cartList.size){
                    val isView = cartPosition != cartList.size-1

                    holder.bind(cartList[cartPosition], isView)
                }

            }
            is PaymentPriceViewHolder -> {
                holder.bind(cartList)
            }

        }
    }

    override fun getItemCount() = mList.size

    override fun getItemViewType(position: Int): Int {
        return mList[position]
    }

    fun setData() {
        mList.clear()
        mList.add(0) //배송지 viewType
        mList.add(1) //주문상품 타이틀 viewType
        cartList?.let {
            it.forEach {
                mList.add(2) // 주문 상품 목록 viewType
            }
        }

        mList.add(3) // 주문 가격 viewType
        mList.add(4) // 결제 수단 viewType
    }




}