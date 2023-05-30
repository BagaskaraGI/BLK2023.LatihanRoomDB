package com.example.latihanroomdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihanroomdb.data.UserViewModel
import com.example.latihanroomdb.databinding.FragmentListBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding : FragmentListBinding
    private var _binding : FragmentListBinding? = null
    private val bindings get() = _binding!!
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater,container,false) //binding yg ini sus
        _binding = FragmentListBinding.inflate(layoutInflater,container,false)


        //Menyiapkan Adapter
        val adapter = UserListAdapter()
        val rvUser = binding.rvUser

        //Menambahkan adapter ke recyclerView
        rvUser.adapter = adapter
        rvUser.layoutManager = LinearLayoutManager(requireContext())

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            user -> adapter.setData(user)
        })
        //Membuat action btnADD


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButtonAdd.setOnClickListener{
            this.findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}