package com.mahima.animestreamingapp.ui.myprofile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myprofileViewModel =
            ViewModelProvider(this).get(MyprofileViewModel::class.java)

        _binding = MyprofileFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textMyprofile
        myprofileViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


