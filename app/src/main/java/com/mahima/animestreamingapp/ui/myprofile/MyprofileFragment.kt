package com.mahima.animestreamingapp.ui.myprofile

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.databinding.MyprofileFragmentBinding
<<<<<<< HEAD
import com.mahima.animestreamingapp.ui.AboutUsFragment
=======
import com.mahima.animestreamingapp.ui.AboutusFragment
>>>>>>> Dashboard
import com.mahima.animestreamingapp.ui.myprofile.MyprofileViewModel

class MyprofileFragment : Fragment() {

    private lateinit var myprofileViewModel: MyprofileViewModel
    private var _binding: MyprofileFragmentBinding ? = null
    private lateinit var aboutus: CardView
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

//        cardview
        aboutus = root.findViewById(R.id.aboutus)
        aboutus.setOnClickListener {
<<<<<<< HEAD
            Toast.makeText(root.context,"Clicked on about us",Toast.LENGTH_LONG).show()
//            to open another fragment
            supportFragmentManager.begin
        }


=======
            Toast.makeText(root.context, "Clicked on about us", Toast.LENGTH_LONG).show()
//            opens fragment
            requireActivity().supportFragmentManager.beginTransaction().apply {

                replace(R.id.linearContainer,AboutusFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
>>>>>>> Dashboard

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



