package org.techtown.shoppingapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.databinding.FragmentProductListBinding

class ProductListFragment : BaseFragment() {

    lateinit var binding : FragmentProductListBinding
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

        arguments?.getInt("id")?.let {
            cateId = it
            Log.d("yj", "id : $cateId")
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