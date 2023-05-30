package com.example.latihanroomdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.latihanroomdb.data.User
import com.example.latihanroomdb.data.UserViewModel
import com.example.latihanroomdb.databinding.FragmentAddBinding


/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding : FragmentAddBinding
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(layoutInflater,container,false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.buttonAdd.setOnClickListener(){
            insertDataToDatabase()
            this.findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        return binding.root
    }


    private fun insertDataToDatabase(){
        val firstName = binding.inputFirstName.text.toString()
        val lastName = binding.inputLastName.text.toString()
        val usia = Integer.parseInt(binding.inputAge.text.toString())

        val user = User(0,firstName,lastName, usia)
        mUserViewModel.addUser(user)
    }
}