package com.mahima.animestreamingapp.ui.myprofile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.databinding.MyprofileFragmentBinding
import com.mahima.animestreamingapp.ui.myprofile.MyprofileViewModel

class MyprofileFragment : Fragment() {

    private lateinit var myprofileViewModel: MyprofileViewModel
    private var _binding: MyprofileFragmentBinding ? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val items = arrayOf("Name","Edit","About Us","Terms and Conditions","Log out")
    private lateinit var listview : ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myprofileViewModel =
            ViewModelProvider(this).get(MyprofileViewModel::class.java)

        _binding = MyprofileFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        for listview
        listview = root.findViewById(R.id.listview)
//        adapter
        val adapter = ArrayAdapter(
            root.context,
            android.R.layout.simple_expandable_list_item_1,
            items
        )
        listview.adapter=adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


