package com.mahima.animestreamingapp.ui.mypet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.databinding.FragmentMypetBinding

class MypetFragment : Fragment() {

    private lateinit var notificationsViewModel: MypetViewModel
    private var _binding: FragmentMypetBinding? = null
    private lateinit var walkcontainer: LinearLayout

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(MypetViewModel::class.java)

        _binding = FragmentMypetBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        binding
        walkcontainer = root.findViewById(R.id.walkcontainer)
//        set on click listener
        walkcontainer.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.containermypet,WalkreminderFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}