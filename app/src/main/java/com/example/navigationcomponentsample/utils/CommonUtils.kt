package com.example.navigationcomponentsample.utils

import android.text.TextUtils
import android.util.Patterns

class CommonUtils {
    companion object{
        fun isEmailValid(email: String): Boolean {
            return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isPasswordValid(password: String): Boolean {
            return !TextUtils.isEmpty(password) && password.length >= 6
        }

        fun isPhoneNum(phoneNum: String): Boolean {
            return TextUtils.isEmpty(phoneNum) || phoneNum.length == 10
        }

        fun isNameValid(name: String): Boolean {
            return !TextUtils.isEmpty(name)
        }
    }
}