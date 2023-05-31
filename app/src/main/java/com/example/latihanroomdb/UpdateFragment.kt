package com.example.latihanroomdb

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.latihanroomdb.data.User
import com.example.latihanroomdb.data.UserViewModel
import com.example.latihanroomdb.databinding.FragmentUpdateBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UpdateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val view = binding.root

        binding.inputFirstName.setText(args.currentUser.firstName)
        binding.inputLastName.setText(args.currentUser.lastName)
        binding.inputAge.setText(args.currentUser.age.toString())


        //Button Update
        binding.buttonUpdate.setOnClickListener(){
            //update data
            updateDataToDatabase(args.currentUser.id)
            //Balik ke listFragment
            this.findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        //Button Delete
        binding.buttonDelete.setOnClickListener(){

            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("Yakin nih bos mau delete data ini?").setCancelable(false)
                .setPositiveButton("Ya"){dialog, id-> deleteDataFromDatabase()}
                .setNegativeButton("Tidak"){dialog, id->}
            val alerts = builder.create()
            alerts.show()

        }

        return view
    }

    private fun updateDataToDatabase(id : Int){
        val firstName = binding.inputFirstName.text.toString()
        val lastName = binding.inputLastName.text.toString()
        val usia = Integer.parseInt(binding.inputAge.text.toString())

        val user = User(id,firstName,lastName, usia)
        mUserViewModel.updateUser(user)
    }

    private fun deleteDataFromDatabase(){
        val firstName = args.currentUser.firstName
        val lastName = args.currentUser.lastName
        val usia = args.currentUser.age
        val id = args.currentUser.id

        val user = User(id,firstName,lastName, usia)
        mUserViewModel.deleteUser(user)

        this.findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }






}