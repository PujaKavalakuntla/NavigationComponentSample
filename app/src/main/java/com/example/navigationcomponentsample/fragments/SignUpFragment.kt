package com.example.navigationcomponentsample.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.navigationcomponentsample.R
import com.example.navigationcomponentsample.databinding.FragmentSignUpBinding
import com.example.navigationcomponentsample.utils.CommonUtils
import com.google.android.material.textfield.TextInputLayout

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    var fragmentSignUpFragment: FragmentSignUpBinding? = null
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSignUpBinding.bind(view)
        sharedPreferences = activity?.getSharedPreferences("myapp", AppCompatActivity.MODE_PRIVATE)
        fragmentSignUpFragment = binding

        binding.btnCreateAccount.setOnClickListener() {
            var isName = true
            var isEmail = true
            var isPassword = true
            val name = binding.tilName.editText?.text.toString()
            val email = binding.tilEmail.editText?.text.toString()
            val password = binding.tilPassword.editText?.text.toString()
            if (!CommonUtils.isNameValid(name)) isName = false
            if (!CommonUtils.isEmailValid(email)) isEmail = false
            if (!CommonUtils.isPasswordValid(password)) isPassword = false

            if (isName && isEmail && isPassword) {
                callLogin(name, email, password, view)
            } else {
                validateFields(isName, isEmail, isPassword, view)
            }
        }
    }

    private fun callLogin(name: String, email: String, password: String, view: View) {
        //save the values to shared preferences
        sharedPreferences!!.edit().putString("name", name).apply()
        sharedPreferences!!.edit().putString("email", email).apply()
        sharedPreferences!!.edit().putString("password", password).apply()
        sharedPreferences!!.edit().putBoolean("login", true).apply()

        val navController = Navigation.findNavController(view)
        navController.navigate(R.id.action_signUpFragment_to_loginFragment)
    }

    private fun validateFields(isName: Boolean, isEmail: Boolean, isPassword: Boolean, view: View) {
        val binding = FragmentSignUpBinding.bind(view)
        fragmentSignUpFragment = binding
        if (!isName) {
            val name = binding.tilName.editText?.text.toString()
            if (name.isEmpty()) {
                showValidationError(binding.tilName, getString(R.string.name_required))
            }
        } else clearError(binding.tilName)
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

    private fun showValidationError(textInputLayout: TextInputLayout?, reason: String) {
        textInputLayout!!.isErrorEnabled = true
        textInputLayout.error = reason
    }

    private fun clearError(til: TextInputLayout) {
        til.isErrorEnabled = false
    }

    override fun onDestroyView() {
        fragmentSignUpFragment = null
        super.onDestroyView()
    }
}