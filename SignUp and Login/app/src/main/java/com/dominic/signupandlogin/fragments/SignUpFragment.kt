package com.dominic.signupandlogin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.dominic.signupandlogin.R
import com.dominic.signupandlogin.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {

    private lateinit var auth : FirebaseAuth
    private lateinit var navControl : NavController
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    binding = FragmentSignUpBinding.inflate(inflater, container, false)
    return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        registerEvents()
    }

    private fun registerEvents() {

        binding.authTextView.setOnClickListener{
            navControl.navigate(R.id.action_signUpFragment_to_signInFragment)
        }

      binding.nextBtn.setOnClickListener {
          val email = binding.emailEt.text.toString()
          val pass  = binding.passEt.text.toString()
          val verifyPass = binding.rePassEt.text.toString()

          if (email.isNotEmpty() && pass.isNotEmpty() && verifyPass.isNotEmpty()){
              if (pass == verifyPass){
                  auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                      if (it.isSuccessful){
                          Toast.makeText(context, "Registered Successfully", Toast.LENGTH_SHORT).show()
                          navControl.navigate(R.id.action_signUpFragment_to_homeFragment)
                      }else{
                          Toast.makeText(context,it.exception?.message, Toast.LENGTH_SHORT).show()
                      }
                  }
              }
          }
      }
    }

    private fun init(view: View) {
        navControl= Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()

    }

}