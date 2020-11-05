package com.example.notificationfire.ServiceFirebase

import com.example.notificationfire.R
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class Service : FirebaseInstanceIdService() {
    override fun onTokenRefresh() {
        val recent_token = FirebaseInstanceId.getInstance().toString()
        val sharedPreferences = applicationContext.getSharedPreferences(
            getString(R.string.FCM_PREF), MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.putString(getString(R.string.FCM_PREF), recent_token)
        editor.commit()
    }
}