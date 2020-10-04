package com.cristina.proyectounidad1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var textNombre : TextView
    private lateinit var textApellido : TextView
    private lateinit var textEmail : TextView
    private lateinit var radio1 : RadioButton
    private lateinit var radio2 : RadioButton
    private lateinit var radio3 : RadioButton
    private lateinit var checkRosa : CheckBox
    private lateinit var checkLaurel : CheckBox
    private lateinit var checkCerezo: CheckBox
    private lateinit var checkGirasol: CheckBox
    private lateinit var checkMargarita: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkRosa = findViewById(R.id.checkRosa)
        checkLaurel = findViewById(R.id.checkLaurel)
        checkCerezo = findViewById(R.id.checkCerezo)
        checkGirasol = findViewById(R.id.checkGirasol)
        checkMargarita = findViewById(R.id.checkMargarita)

        checkRosa.setOnCheckedChangeListener(changeChecked)
        checkLaurel.setOnCheckedChangeListener(changeChecked)
        checkCerezo.setOnCheckedChangeListener(changeChecked)
        checkGirasol.setOnCheckedChangeListener(changeChecked)
        checkMargarita.setOnCheckedChangeListener(changeChecked)

        textNombre = findViewById(R.id.textNombre)
        textApellido = findViewById(R.id.textApellido)
        textEmail = findViewById(R.id.textEmail)

        radio1 = findViewById(R.id.radio1)
        radio2 = findViewById(R.id.radio2)
        radio3 = findViewById(R.id.radio3)

        val fabActionDialog = findViewById<FloatingActionButton>(R.id.fabActionDialog)
        fabActionDialog.setOnClickListener(fabClick)
    }

    private val changeChecked = CompoundButton.OnCheckedChangeListener{ button, checked ->
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val flowerAdapter = FlowerAdapter(createFlowers())
        recyclerView.layoutManager = LinearLayoutManager(this,  LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = flowerAdapter
        flowerAdapter.notifyDataSetChanged()
    }

    fun createFlowers(): List<Flower>{
        val flowers = mutableListOf<Flower>()

        if(checkRosa.isChecked){
            flowers.add(
                Flower("Rosa", R.drawable.ic_rosa)
            )
        }
        if(checkLaurel.isChecked){
            flowers.add(
                Flower("Laurel", R.drawable.ic_oleander)
            )
        }
        if(checkCerezo.isChecked){
            flowers.add(
                Flower("Cerezo", R.drawable.ic_flor_de_cerezo)
            )
        }
        if(checkGirasol.isChecked){
            flowers.add(
                Flower("Girasol", R.drawable.ic_girasol)
            )
        }
        if(checkMargarita.isChecked){
            flowers.add(
                Flower("Margarita", R.drawable.ic_margarita)
            )
        }
        return flowers
    }

    private val fabClick = View.OnClickListener { fab ->

        var tip = ""
        var op  = ""

        if(radio1.isChecked){
            tip += "\nApartar"
        }
        if (radio2.isChecked){
            tip += "\nOrdene y recoja"
        }
        if (radio1.isChecked){
            tip += "\nComprar"
        }

        if (checkRosa.isChecked){
            op += "\nRosa"
        }
        if (checkLaurel.isChecked){
            op += "\nLaurel"
        }
        if (checkCerezo.isChecked){
            op += "\nCerezo"
        }
        if (checkGirasol.isChecked){
            op += "\nGirasol"
        }
        if (checkMargarita.isChecked){
            op += "\nMargarita"
        }

        var msj = "NOMBRE\n"  + textNombre.text.toString() + "\nAPELLIDO\n" + textApellido.text.toString() +  "\nE-MAIL\n" + textEmail.text.toString() +  "\nTIPO DE SERVICIO" +
                tip +  "\nOPCIÓN" + op
        val alertDialog = AlertDialog.Builder(fab.context)
            .setTitle("〜INFORMACIÓN〜")
            .setMessage(msj)
            .setPositiveButton("OK", null)
            .create()
        alertDialog.show()
    }
}