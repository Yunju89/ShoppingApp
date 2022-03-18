package org.techtown.shoppingapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.databinding.FragmentCartBinding
import org.techtown.shoppingapp.databinding.FragmentHomeBinding

class CartFragment : Fragment() {

    lateinit var binding : FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        return  binding.root
    }
}