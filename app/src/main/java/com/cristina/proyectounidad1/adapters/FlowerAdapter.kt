package com.cristina.proyectounidad1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cristina.proyectounidad1.models.entities.Flower
import com.cristina.proyectounidad1.R

class FlowerAdapter(private val flowers : List<Flower>): RecyclerView.Adapter<FlowerAdapter.FlowerHolder>(){

    class FlowerHolder(val view : View) : RecyclerView.ViewHolder(view){
        fun onBind(flower: Flower){
            val title = view.findViewById<TextView>(R.id.title)
            val image = view.findViewById<ImageView>(R.id.image)
            title.text = flower.title
            image.setImageResource(flower.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card_flowers,parent,false)
        return FlowerHolder(view)
    }

    override fun onBindViewHolder(flowerHolder: FlowerHolder, position: Int) {
        val flower = flowers[position]
        flowerHolder.onBind(flower)
    }

    override fun getItemCount(): Int {
        return flowers.size
    }
}