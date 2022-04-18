package org.techtown.shoppingapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.adapters.ShipmentListAdapter
import org.techtown.shoppingapp.api.APIList
import org.techtown.shoppingapp.api.ServerAPI
import org.techtown.shoppingapp.databinding.FragmentMyShipmentInfoBinding
import org.techtown.shoppingapp.datas.BasicResponse
import org.techtown.shoppingapp.datas.UserAllAddressData
import org.techtown.shoppingapp.interfaces.ShipmentDeletedListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class MyShipmentInfoFragment(
) : DialogFragment(), ShipmentDeletedListener {

    lateinit var binding : FragmentMyShipmentInfoBinding
    lateinit var mShipListAdapter : ShipmentListAdapter
    var mList : ArrayList<UserAllAddressData> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Log.d("yj", "onCreate")
        setStyle(STYLE_NO_TITLE, R.style.dialog_fullScreen)
        isCancelable = true

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Log.d("yj", "onCreateView")
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_my_shipment_info, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        Log.d("yj", "onViewCreated")

        setValues()
        setupEvents()

    }

    fun setupEvents(){
        binding.btnExit.setOnClickListener {
            dismiss()
        }




    }


    fun setValues(){
        mShipListAdapter = ShipmentListAdapter(mList, this )
        binding.myShipmentRecyclerView.adapter = mShipListAdapter
        binding.myShipmentRecyclerView.layoutManager = LinearLayoutManager(context)



    }

    fun getData(mShipList : ArrayList<UserAllAddressData>){
        mList.clear()
        mList.addAll(mShipList)

    }

    override fun onDeletedShipment(id : Int) {
        val retrofit = ServerAPI.getRetrofit(requireContext())
        val apiList = retrofit.create(APIList::class.java)

        apiList.getRequestDeleteShipmentInfo(id).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }


}