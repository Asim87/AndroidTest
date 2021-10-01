package com.example.testgradle.util

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import android.net.ConnectivityManager


class Utility {

    companion object {
        private val EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )

        fun isValidEmail(str: String): Boolean{
            return EMAIL_ADDRESS_PATTERN.matcher(str).matches()
        }

        fun showToast(message:String, context: Context) {
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
        }

        fun getCurrentDate() : String {
            val sdf = SimpleDateFormat("dd-MM-yyyy")
            return sdf.format(Date())
        }
        fun isInternetConnected(mContext: Context): Boolean {
            val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
    }
}