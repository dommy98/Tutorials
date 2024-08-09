package com.dominic.signupandlogin.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.dominic.signupandlogin.R
import com.google.firebase.auth.FirebaseAuth

class SplashFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var navControl: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val auth = FirebaseAuth.getInstance()
        val navControl = Navigation.findNavController(view)

        Handler(Looper.myLooper()!!).postDelayed({
            if (auth.currentUser != null){
                navControl.navigate(R.id.action_splashFragment_to_homeFragment)
            }else{
                navControl.navigate(R.id.action_splashFragment_to_signInFragment)
            }
        }, 2000)
    }

}