package org.techtown.shoppingapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.adapters.FindAddressRecyclerAdapter
import org.techtown.shoppingapp.api.APIList
import org.techtown.shoppingapp.api.KakaoAPI
import org.techtown.shoppingapp.databinding.DialogFindAddressBinding
import org.techtown.shoppingapp.interfaces.FindZipcodeListener
import org.techtown.shoppingapp.kakaodatas.kakaodata
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FindAddressDialog() : DialogFragment() {

    lateinit var binding: DialogFindAddressBinding

    lateinit var kakaodata : kakaodata
    var findZipcodeListener : FindZipcodeListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.dialog_fullScreen)
        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_find_address, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRequestKakaoAddress()

    }

    fun getRequestKakaoAddress() {

        binding.btnFindAddress.setOnClickListener {

            val edtAddress = binding.edtAddress.text.toString().trim()

            if (edtAddress.isNotEmpty()) {

                val retrofit = KakaoAPI.getKakaoRetrofit()
                val apiList = retrofit.create(APIList::class.java)


                apiList.getKakaoAddress(edtAddress).enqueue(object : Callback<kakaodata> {
                    override fun onResponse(call: Call<kakaodata>, response: Response<kakaodata>) {
//                        Log.d("yj", "${call.request().url()}")
//                        Log.d("yj", "${response.body()}")

                        response.body()?.let {

                            kakaodata = it

                            setValues()
                        }
                    }

                    override fun onFailure(call: Call<kakaodata>, t: Throwable) {
                        Log.d("yj", "kakao실패 ${t.message}")
                    }

                })
            }

        }

    }

    fun setValues() {

        findZipcodeListener?.let {
            val findAddressAdapter = FindAddressRecyclerAdapter(kakaodata.getList(), it)
            binding.findAddressRecyclerView.adapter = findAddressAdapter
        }


    }

    fun setListener(listener : FindZipcodeListener) {
        findZipcodeListener = listener
    }

}