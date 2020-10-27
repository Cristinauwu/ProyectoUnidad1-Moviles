package com.cristina.proyectounidad1.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cristina.proyectounidad1.R
import com.cristina.proyectounidad1.adapters.ContentAdapter
import com.cristina.proyectounidad1.models.entities.Content
import com.cristina.proyectounidad1.models.entities.Flower
import com.cristina.proyectounidad1.models.roomdb.ContentDB
import com.cristina.proyectounidad1.viewmodels.ActivityAddInfoViewModel
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class ActivityAddInfo : AppCompatActivity() {

    private val activityAddInfoViewModel: ActivityAddInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_info)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_content)
        recyclerView.layoutManager = LinearLayoutManager(this)

        activityAddInfoViewModel.getAllContent().observe(this,{contents ->
            val contentAdapter = ContentAdapter(contents)
            recyclerView.adapter = contentAdapter
            contentAdapter.notifyDataSetChanged()
        })
    }
}