package com.mahima.animestreamingapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.adapter.adapterForRecyclerView
import com.mahima.animestreamingapp.database.PetProductDatabase
import com.mahima.animestreamingapp.databinding.FragmentHomeBinding
import com.mahima.animestreamingapp.entity.PetEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var tvpet : TextView
    private lateinit var fabaddpet : FloatingActionButton
    private lateinit var recyclerview: RecyclerView

    companion object{
        val PetList: ArrayList<PetEntity> = ArrayList<PetEntity>()
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        binding views
        tvpet = root.findViewById(R.id.tvpet)
        fabaddpet=root.findViewById(R.id.fabaddpet)
        recyclerview=root.findViewById(R.id.recyclerview)

        val adapter = adapterForRecyclerView(PetList)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        recyclerview.adapter=adapter
        PetList.clear()
        getpet()

//        open pet form
        fabaddpet.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.linearContainer,AddPetFragment())
                    .addToBackStack(null)
                    .commit()
            }
            fabaddpet.visibility=View.INVISIBLE
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getpet(){
        //        get data
        CoroutineScope(Dispatchers.IO).launch {
            val pet = PetProductDatabase.getDatabase(requireContext()).petDao().getPet()
            if (pet.equals(null)){
                tvpet.setText("No pets to show. Add one")
            }else{
                for(i in pet){
                    PetList.add(
                        PetEntity(i.id,
                            i.petName,
                            i.petAge,
                            i.petGender,
                            i.petType
                        )
                    )
                }

            }
        }
    }
}
