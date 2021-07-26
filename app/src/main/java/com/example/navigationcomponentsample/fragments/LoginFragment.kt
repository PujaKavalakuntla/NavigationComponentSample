package com.example.navigationcomponentsample.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.navigationcomponentsample.R
import com.example.navigationcomponentsample.databinding.FragmentLoginBinding
import com.example.navigationcomponentsample.utils.CommonUtils
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private var fragmentLoginBinding: FragmentLoginBinding? = null
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = activity?.getSharedPreferences("myapp", AppCompatActivity.MODE_PRIVATE)
        binding = FragmentLoginBinding.bind(view)

        val name = sharedPreferences?.getString("name", "")
        val email = sharedPreferences?.getString("email", "")
        val password = sharedPreferences?.getString("password", "")

        binding.btnLogin.setOnClickListener {
            val mEmail = binding.tilEmail.editText?.text.toString()
            val mPassword = binding.tilPassword.editText?.text.toString()
            var isEmail = true
            var isPassword = true
            if (!CommonUtils.isEmailValid(mEmail)) isEmail = false
            if (!CommonUtils.isPasswordValid(mPassword)) isPassword = false
            if (isEmail && isPassword) {
                if (email == mEmail && password == mPassword) {
                    if (name != null) {
                        login(name, email, view)
                    }
                } else {
                    Toast.makeText(activity, "Invalid USer ", Toast.LENGTH_LONG).show()
                }
            } else {
                validateFields(isEmail, isPassword)
            }
        }
    }

    private fun validateFields(isEmail: Boolean, isPassword: Boolean) {
        if (!isEmail) {
            val email = binding.tilEmail.editText?.text.toString()
            if (email.isNotEmpty()) {
                showValidationError(binding.tilEmail, getString(R.string.invalid_email))
            } else showValidationError(binding.tilEmail, getString(R.string.email_req))
        } else clearError(binding.tilEmail)

        if (!isPassword) {
            val password = binding.tilPassword.editText!!.text
            if (password.isNotEmpty()) showValidationError(
                binding.tilPassword,
                getString(R.string.invalid_password)
            )
            else showValidationError(binding.tilPassword, getString(R.string.password_req))
        } else clearError(binding.tilPassword)
    }

    private fun login(name: String, email: String, view: View) {
        sharedPreferences!!.edit().putBoolean("login", true).apply()
        // passing data with safe args
        //val user = User(name, email)
        //val action = LoginFragmentDirections.actionLoginFragmentToProfileFragment(user)
        val navController = Navigation.findNavController(view)
        navController.navigate(R.id.action_loginFragment_to_profileFragment)

        //navController.navigate(action)
    }

    private fun showValidationError(textInputLayout: TextInputLayout?, reason: String) {
        textInputLayout!!.isErrorEnabled = true
        textInputLayout.error = reason
    }

    private fun clearError(til: TextInputLayout) {
        til.isErrorEnabled = false
    }

    override fun onDestroyView() {
        fragmentLoginBinding = null
        sharedPreferences!!.edit().putBoolean("login", false).apply()
        super.onDestroyView()
    }

}