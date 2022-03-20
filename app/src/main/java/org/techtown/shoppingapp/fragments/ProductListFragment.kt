package org.techtown.shoppingapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.adapters.ProductListAdapter
import org.techtown.shoppingapp.databinding.FragmentProductListBinding
import org.techtown.shoppingapp.datas.BasicResponse
import org.techtown.shoppingapp.datas.ProductsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListFragment : BaseFragment() {

    lateinit var binding : FragmentProductListBinding
    val mProductList = ArrayList<ProductsResponse>()
    lateinit var mProductAdapter : ProductListAdapter

    var cateId = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

//        받아온 대카테고리 아이디 (cateId가 null 인지 체크하기)
        arguments?.getInt("id")?.let {
            cateId = it
            Log.d("yj", "id : $cateId")
        }

        mProductAdapter = ProductListAdapter(mProductList)
        binding.productListRecyclerView.adapter = mProductAdapter
        binding.productListRecyclerView.layoutManager = LinearLayoutManager(mContext)



//        cateId 유효성 체크
        if(cateId > -1) {
            apiList.getRequestProduct(0, cateId).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    Log.d("yj", "onResponse : ${response.body()?.message}")

                    if (response.isSuccessful) {
                        val br = response.body()!!

                        mProductList.clear()
                        mProductList.addAll(br.data.products)
                        mProductAdapter.notifyDataSetChanged()



                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                    Log.d("yj", "onFail : ${t.message}")
                }


            })
        }



    }

    companion object {
        fun newInstance(id : Int): ProductListFragment{
            val args = Bundle()
            val fragment = ProductListFragment()

            args.putInt("id", id)

            fragment.arguments = args
            return fragment
        }
    }

}