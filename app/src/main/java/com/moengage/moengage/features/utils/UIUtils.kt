package com.moengage.moengage.features.utils

import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging

object UIUtils {

    fun showErrorAlert(error: String, isShortDuration: Boolean = true) {
        Toast.makeText(NewsApp.mContext, error, if (isShortDuration) Toast.LENGTH_SHORT else Toast.LENGTH_LONG).show()
    }

    fun getToke(){
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (task.result != null && !TextUtils.isEmpty(task.result)) {
                        val token: String = task.result!!
                        Log.e("TAG", "onNewToken: "+token )
                    }
                }
            }
    }

}