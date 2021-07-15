package com.mahima.animestreamingapp.ui.myprofile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mahima.animestreamingapp.R

class MyprofileFragment : Fragment() {

    companion object {
        fun newInstance() = MyprofileFragment()
    }

    private lateinit var viewModel: MyprofileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.myprofile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyprofileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}