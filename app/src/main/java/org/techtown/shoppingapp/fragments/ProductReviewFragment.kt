package org.techtown.shoppingapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.adapters.ProductReviewRecyclerAdapter
import org.techtown.shoppingapp.databinding.FragmentProductReviewBinding
import org.techtown.shoppingapp.datas.ProductsResponse
import org.techtown.shoppingapp.datas.ReviewsResponse

class ProductReviewFragment : BaseFragment() {
    lateinit var binding : FragmentProductReviewBinding

    lateinit var data : ProductsResponse
    var mReviewList = ArrayList<ReviewsResponse>()

    lateinit var mAdapter : ProductReviewRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_review, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        data = arguments?.getSerializable("product") as ProductsResponse
        mReviewList = data.reviews

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mAdapter = ProductReviewRecyclerAdapter(mReviewList)
        binding.reviewRecyclerView.adapter = mAdapter
        binding.reviewRecyclerView.layoutManager = LinearLayoutManager(mContext)

    }

    companion object{
        fun newInstance(data : ProductsResponse): ProductReviewFragment {
            val args = Bundle()
            val fragment = ProductReviewFragment()

            args.putSerializable("product", data)

            fragment.arguments = args
            return fragment
        }
    }

}