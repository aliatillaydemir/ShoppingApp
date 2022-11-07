package com.ayd.shoppingapp.ui.authentication.authenticationScreens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ayd.shoppingapp.MainActivity
import com.ayd.shoppingapp.R
import com.ayd.shoppingapp.databinding.FragmentLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin.setOnClickListener {

        val email = binding.signInEmail.text.toString()
        val password = binding.signInPassword.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(requireContext(),"Registration Successful.", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_authPagerFragment_to_productsFragment)
                    }else{
                        Toast.makeText(requireContext(),it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

        }else{
            Toast.makeText(requireContext(),"Fill the fields", Toast.LENGTH_SHORT).show()
        }
    }

    }


    override fun onStart() {
        super.onStart()
        if(firebaseAuth.currentUser != null){
            findNavController().navigate(R.id.action_authPagerFragment_to_productsFragment)
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).hideBottomNavigation()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}