package org.techtown.shoppingapp

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.shoppingapp.adapters.ShipmentListAdapter
import org.techtown.shoppingapp.databinding.ActivityMyShipmentInfoBinding
import org.techtown.shoppingapp.datas.BasicResponse
import org.techtown.shoppingapp.datas.UserAllAddressData
import org.techtown.shoppingapp.fragments.MyShipmentAddDialog
import org.techtown.shoppingapp.interfaces.ShipmentChangedListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyShipmentInfoActivity : BaseActivity(), ShipmentChangedListener {

    lateinit var binding : ActivityMyShipmentInfoBinding
    lateinit var mShipListAdapter : ShipmentListAdapter
    var mList : ArrayList<UserAllAddressData> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_my_shipment_info)

        setupEvents()
        getShipmentInfo()

    }

    override fun setupEvents() {
        binding.btnExit.setOnClickListener {
            finish()
        }

        binding.btnNewAddress.setOnClickListener {
            val dialog = MyShipmentAddDialog()
            dialog.show(supportFragmentManager, "CustomDialog2")
        }

    }

    override fun setValues() {

        mShipListAdapter = ShipmentListAdapter(mList, this )
        binding.myShipmentRecyclerView.adapter = mShipListAdapter
        binding.myShipmentRecyclerView.layoutManager = LinearLayoutManager(mContext)


    }

    fun getShipmentInfo(){
        apiList.getRequestShipmentInfo().enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful){
                    val br = response.body()!!

                    mList.clear()
                    mList.addAll(br.data.user_all_address)

                    setValues()


                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }



    override fun onChangedShipment(id: Int) {

    }



}