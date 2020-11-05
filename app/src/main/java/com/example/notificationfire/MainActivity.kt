package com.example.notificationfire

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.iid.FirebaseInstanceId

class MainActivity : AppCompatActivity() {
    private var contactViewModel: ContactViewModel? = null
    var message: String? = null
    var title: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("ui", intent.extras.toString())
        Log.d("Token", FirebaseInstanceId.getInstance().token!!)
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(mHandler, IntentFilter("com.example.roomdatabasemethod_FCM-MESSAGE"))
        if (intent.extras != null) {
            for (key in intent.extras!!.keySet()) {
                Log.d("uo", intent.extras!!.keySet().toString())
                if (key == "title") {
                    Log.d("uh", intent.extras!!.getString("title").toString())
                    title = intent.extras!!.getString("title")
                } else if (key == "message") {
                    Log.d("uj", intent.extras!!.getString("message").toString())
                    message = intent.extras!!.getString("message")
                }
            }
        }
        contactViewModel?.insert(title?.let { message?.let { it1 -> Contact(it, it1) } })
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val adapter = ContactAdapter()
        recyclerView.setAdapter(adapter)
        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        contactViewModel!!.allContacts.observe(this,
            Observer<List<Contact?>?> { contacts -> adapter.setContacts(contacts as List<Contact>) })
    }

    private val mHandler: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val title = intent.getStringExtra("title")
            val message = intent.getStringExtra("message")
            Log.d("vi", title!!)
            Log.d("ve", message!!)
        }
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mHandler)
    }

    companion object {
        private val instance: ContactDatabase? = null
    }
}