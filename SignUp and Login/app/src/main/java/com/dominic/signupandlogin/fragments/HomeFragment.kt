package com.dominic.signupandlogin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.dominic.signupandlogin.R
import com.dominic.signupandlogin.databinding.FragmentHomeBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HomeFragment : Fragment(), AddTodoPopupFragment.DialogNextBtnClickListener {

    private lateinit var navControl: NavController
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentHomeBinding
    private lateinit var popupFragment: AddTodoPopupFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,  container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        registerEvents()
    }

    private fun registerEvents() {
        binding.addBtnHome.setOnClickListener {
            popupFragment = AddTodoPopupFragment()
            popupFragment.setListener(this)
            popupFragment.show(
                childFragmentManager,
                "AddTodoPopupFragment"

            )
        }
    }

    private fun init(view: View) {
        navControl = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference.child("Tasks")
            .child(auth.currentUser?.uid.toString())
    }

    override fun onSaveTask(todo: String, todoEt: TextInputEditText) {
        database.push().setValue(todo).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(context, "Saved Successfully!!", Toast.LENGTH_SHORT).show()
                todoEt.text = null
            }else{
                Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
            popupFragment.dismiss()
        }
    }


}