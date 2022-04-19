package org.techtown.shoppingapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import org.techtown.shoppingapp.R
import org.techtown.shoppingapp.databinding.FragmentMyShipmentAddBinding

class MyShipmentAddFragment : DialogFragment() {

    lateinit var binding : FragmentMyShipmentAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.dialog_fullScreen)
        isCancelable = true

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_shipment_add, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupEvents()


    }

    fun setupEvents(){
        binding.btnFindAddress.setOnClickListener {
            val dialog = FindAddressFragment()
            dialog.show(childFragmentManager, "findAddress")
        }
    }

}