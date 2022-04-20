package org.techtown.shoppingapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import org.json.JSONObject
import org.techtown.shoppingapp.MyShipmentInfoActivity
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.api.APIList
import org.techtown.shoppingapp.api.ServerAPI
import org.techtown.shoppingapp.databinding.DialogMyShipmentAddBinding
import org.techtown.shoppingapp.datas.BasicResponse
import org.techtown.shoppingapp.interfaces.FindZipcodeListener
import org.techtown.shoppingapp.interfaces.ShipmentAddListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyShipmentAddDialog : DialogFragment(), FindZipcodeListener {

    lateinit var binding: DialogMyShipmentAddBinding
    val dialog = FindAddressDialog()
    var shipmentAddListener: ShipmentAddListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.dialog_fullScreen)
        isCancelable = true

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_my_shipment_add, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupEvents()


    }

    fun setupEvents() {
        binding.btnFindAddress.setOnClickListener {

            dialog.setListener(this)
            dialog.show(childFragmentManager, "findAddress")
        }



        binding.btnSaveAddress.setOnClickListener {

            val name = binding.edtName.text.toString()
            val phone = binding.edtPhone.text.toString()
            val zipCode = binding.txtZipcode.text.toString()
            val address1 = binding.txtAddress1.text.toString()
            val address2 = binding.txtAddress2.text.toString()
            val basicAddress = binding.checkedBasicAddress.isChecked
            val memo = binding.edtMemo.text.toString()

            when {
                name.isEmpty() -> {
                    Toast.makeText(requireContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                phone.isEmpty() -> {
                    Toast.makeText(requireContext(), "전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                zipCode.isEmpty() -> {
                    Toast.makeText(requireContext(), "주소 검색을 해주세요.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                address1.isEmpty() -> {
                    Toast.makeText(requireContext(), "주소 검색을 해주세요.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                address2.isEmpty() -> {
                    Toast.makeText(requireContext(), "상세 주소를 입력해주세요.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            Log.d("yj", "$name $phone $zipCode $address1 $address2 $basicAddress $memo")

            val retrofit = ServerAPI.getRetrofit(requireContext())
            val apiList = retrofit.create(APIList::class.java)

            apiList.postRequestAddShipmentInfo(
                name,
                phone,
                zipCode,
                address1,
                address2,
                basicAddress,
                memo
            ).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    Log.d("yj", "${call.request().url()}")
                    Log.d("yj", "${call.request().body().toString()}")
                    if (response.isSuccessful) {
                        Toast.makeText(requireContext(), "배송지 등록이 완료되었습니다.", Toast.LENGTH_SHORT)
                            .show()

                        shipmentAddListener?.shipmentAdd()



                    } else {
                        val jsonObj = JSONObject(response.errorBody()!!.string())
                        val message = jsonObj.getString("message")

                        Log.d("yj", "배송지등록 $message")

                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                    Log.d("yj", "주소추가 ${t.message}")
                }

            })
        }

    }

    override fun onFindShipment(Address: String, zipCode: String) {
        binding.txtAddress1.text = Address
        binding.txtZipcode.text = zipCode
        dialog.dismiss()

    }

    fun setShipmentListener(listener: ShipmentAddListener){
        shipmentAddListener = listener
    }


}