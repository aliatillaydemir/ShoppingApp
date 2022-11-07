package com.ayd.shoppingapp.ui.authentication.authenticationScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ayd.shoppingapp.R
import com.ayd.shoppingapp.databinding.FragmentRegistrationBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val email = binding.signUpEmail.text.toString()
            val password = binding.signUpPassword.text.toString()
            val confirm = binding.signUpConfirm.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && confirm.isNotEmpty()){
                if(password == confirm){
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(requireContext(),"Registration Successful.", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_authPagerFragment_to_productsFragment)
                        }else{
                            Toast.makeText(requireContext(),it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(requireContext(),"password is not matching", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(requireContext(),"Fill the fields", Toast.LENGTH_SHORT).show()
            }


        }

    }


}