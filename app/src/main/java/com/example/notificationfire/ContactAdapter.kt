package com.example.notificationfire

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.util.*

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactHolder>() {
    private var contacts: List<Contact> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ContactHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val currContact = contacts[position]
        holder.name.setText(currContact.name)
        holder.number.setText(currContact.number)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    fun setContacts(contacts: List<Contact>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }

    inner class ContactHolder(itemView: View) :
        ViewHolder(itemView) {
        val name: TextView
        val number: TextView

        init {
            name = itemView.findViewById(R.id.contactName)
            number = itemView.findViewById(R.id.contactNumber)
        }
    }
}
