package org.techtown.shoppingapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.json.JSONObject
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.adapters.ShipmentListAdapter
import org.techtown.shoppingapp.api.APIList
import org.techtown.shoppingapp.api.ServerAPI
import org.techtown.shoppingapp.databinding.FragmentMyShipmentInfoBinding
import org.techtown.shoppingapp.datas.BasicResponse
import org.techtown.shoppingapp.datas.UserAllAddressData
import org.techtown.shoppingapp.interfaces.ShipmentChangedListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyShipmentInfoFragment(
) : DialogFragment(), ShipmentChangedListener {

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

//    Delete 서버에서 안됨. 버튼 및 인터페이스 수정으로 변경

    override fun onChangedShipment(id : Int) {
        val retrofit = ServerAPI.getRetrofit(requireContext())
        val apiList = retrofit.create(APIList::class.java)

//        apiList.getRequestChangedShipmentInfo(id).enqueue(object : Callback<BasicResponse>{
//            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
//                if (response.isSuccessful){
//                    Toast.makeText(requireContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show()
//                }
//                else {
//                    val jsonObj = JSONObject(response.errorBody()?.string())
//                    val br = jsonObj.getString("message")
//                    Toast.makeText(requireContext(), "$br", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
//                Log.d("yj", t.message.toString())
//            }
//
//        })

    }


}