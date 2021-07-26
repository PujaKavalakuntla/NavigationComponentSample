package com.example.navigationcomponentsample.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.navigationcomponentsample.R
import com.example.navigationcomponentsample.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {
    private var navController: NavController? = null
    private var sharedPreferences: SharedPreferences? = null
    private var fragmentWelcomeBinding: FragmentWelcomeBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentWelcomeBinding.bind(view)
        fragmentWelcomeBinding = binding
        navController = Navigation.findNavController(view)
        binding.btnLogin.setOnClickListener() {
            navController!!.navigate(R.id.action_welcomeFragment_to_loginFragment)
        }
        binding.btnSignUp.setOnClickListener() {
            navController!!.navigate(R.id.action_welcomeFragment_to_signUpFragment)
        }
        checkIsLogin(view)
    }

    private fun checkIsLogin(view: View) {

        sharedPreferences = activity?.getSharedPreferences("myapp", AppCompatActivity.MODE_PRIVATE)
        val name = sharedPreferences?.getString("name", "")
        val email = sharedPreferences?.getString("email", "")
        val password = sharedPreferences?.getString("password", "")

        if (name != "" && email != "" && password != "") {
            login(name.toString(), email.toString(), view)
        }
    }

    private fun login(name: String, email: String, view: View) {
        sharedPreferences!!.edit().putBoolean("login", true).apply()
        // passing data with safe args
        //val user = User(name, email)
        //val action = WelcomeFragmentDirections.actionWelcomeFragmentToProfileFragment(user)
        val navController = Navigation.findNavController(view)
        navController.navigate(R.id.action_welcomeFragment_to_profileFragment)
        //navController.navigate(action)

        // Just navigate to one fragment to other
        /*
        navController.navigate(R.id.action_loginFragment_to_profileFragment)*/
    }
    override fun onDestroyView() {
        fragmentWelcomeBinding = null
        super.onDestroyView()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }
}