package com.cristina.proyectounidad1.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cristina.proyectounidad1.models.entities.Flower
import com.cristina.proyectounidad1.adapters.FlowerAdapter
import com.cristina.proyectounidad1.R
import com.cristina.proyectounidad1.adapters.ContentAdapter
import com.cristina.proyectounidad1.models.dao.ContentDAO
import com.cristina.proyectounidad1.models.entities.Content
import com.cristina.proyectounidad1.models.roomdb.ContentDB
import com.cristina.proyectounidad1.viewmodels.ActivityAddInfoViewModel
import com.cristina.proyectounidad1.viewmodels.MainActivityViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

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
    private val activityAddInfViewModel: ActivityAddInfoViewModel by viewModels()
    private var tip = ""
    private var op  = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fabAdd = findViewById<FloatingActionButton>(R.id.fab_add_content)
        fabAdd.setOnClickListener{
            val intent = Intent(this, ActivityAddInfo::class.java)
            startActivity(intent)
        }

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

        val  buttonSave = findViewById<MaterialButton>(R.id.button_add)
        buttonSave.setOnClickListener(addClick)
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
            flowers.add(Flower("Rosa", R.drawable.ic_rosa))
        }
        if(checkLaurel.isChecked){
            flowers.add(Flower("Laurel", R.drawable.ic_oleander))
        }
        if(checkCerezo.isChecked){
            flowers.add(Flower("Cerezo", R.drawable.ic_flor_de_cerezo))
        }
        if(checkGirasol.isChecked){
            flowers.add(Flower("Girasol", R.drawable.ic_girasol))
        }
        if(checkMargarita.isChecked){
            flowers.add(Flower("Margarita", R.drawable.ic_margarita))
        }
        return flowers
    }

    val fabClick = View.OnClickListener { fab ->
        getInfo()

        var msj = "NOMBRE\n"  + textNombre.text.toString() + "\nAPELLIDO\n" + textApellido.text.toString() +  "\nE-MAIL\n" + textEmail.text.toString() +  "\nTIPO DE SERVICIOO\n" +
                tip +  "\nOPCIÓN" + op
        val alertDialog = AlertDialog.Builder(fab.context)
            .setTitle("〜INFORMACIÓN〜")
            .setMessage(msj)
            .setPositiveButton("OK", null)
            .create()
        alertDialog.show()
    }

    val addClick = View.OnClickListener{ btn ->
        getInfo()
            val name = textNombre.text.toString()
            val lastName = textApellido.text.toString()
            val email = textEmail.text.toString()
            val serv = tip
            val opt = op

            val content = Content(
                name = name,
                lastName = lastName,
                email = email,
                service = serv,
                option = opt
            )

            activityAddInfViewModel.insertContent(content).observe(
                this,{ succesful ->
                    if (succesful){
                        Toast.makeText(this,"Guardado exitosamente",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this,"No se completo el registro",Toast.LENGTH_LONG).show()
                    }
                }
            )
    }

    private fun getInfo() {
        tip=""
        op=""
        if(radio1.isChecked){
            tip = "Apartar"
        }
        if (radio2.isChecked){
            tip = "Ordene y recoja"
        }
        if (radio3.isChecked){
            tip = "Comprar"
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

    }
}