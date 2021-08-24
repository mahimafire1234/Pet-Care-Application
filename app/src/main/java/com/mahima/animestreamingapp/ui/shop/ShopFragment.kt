package com.mahima.animestreamingapp.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.adapter.adapterForViewpager
import com.mahima.animestreamingapp.databinding.FragmentShopBinding

class ShopFragment : Fragment() {

    private lateinit var dashboardViewModel: ShopViewModel
    private var _binding: FragmentShopBinding? = null
    private lateinit var viewpager:ViewPager2
    private lateinit var tabLayout: TabLayout
//    create array lists
    private lateinit var lstTitle : ArrayList<String>
    private lateinit var lstFragments : ArrayList<Fragment>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(ShopViewModel::class.java)

        _binding = FragmentShopBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        declaring variables
        viewpager=root.findViewById(R.id.viewpager)
        tabLayout=root.findViewById(R.id.tablayout)

//        function to populate
        populate()

//        working with adapters
        val adapter = adapterForViewpager(
            lstFragments,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        viewpager.adapter =adapter
        TabLayoutMediator(tabLayout,viewpager){
            tab,position->
            tab.text=lstTitle[position]
        }.attach()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun populate(){
//        populating the arrays above
        lstTitle= ArrayList<String>()
        lstTitle.add("Pet Items")
        lstTitle.add("Pet Care Takers")

        lstFragments= ArrayList<Fragment>()
        lstFragments.add(PetItemFragment())
        lstFragments.add(PetCareTakerFragment())
    }
}
