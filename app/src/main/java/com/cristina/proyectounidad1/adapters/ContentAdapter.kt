package com.cristina.proyectounidad1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cristina.proyectounidad1.R
import com.cristina.proyectounidad1.models.entities.Content

class ContentAdapter(private  val contents: List<Content>) : RecyclerView.Adapter<ContentAdapter.ContentAdapterViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.information,parent,false)

        return ContentAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentAdapterViewHolder, position: Int) {
        val content = contents[position]
        holder.onBind(content)
    }

    override fun getItemCount(): Int {
        return contents.size
    }

   inner class ContentAdapterViewHolder(private val view:View) : RecyclerView.ViewHolder(view){
        fun onBind(content: Content){

            val textViewName = view.findViewById<TextView>(R.id.textview_name)
            val textViewLastName = view.findViewById<TextView>(R.id.textview_lastName)
            val textViewEmail = view.findViewById<TextView>(R.id.textview_email)
            val textViewService = view.findViewById<TextView>(R.id.textview_service)
            val textViewOption = view.findViewById<TextView>(R.id.textview_option)

            textViewName.text = content.name
            textViewLastName.text = content.lastName
            textViewEmail.text = content.email
            textViewService.text = content.service
            textViewOption.text = content.option

        }
    }
}