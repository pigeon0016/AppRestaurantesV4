package com.example.apprestaurantesv4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class PasoIRestaurantDetails : AppCompatActivity() {
    private lateinit var id: String
    private lateinit var calificacion: String
    private lateinit var costo: String
    private lateinit var foto: String
    private lateinit var fundacion: String
    private lateinit var galeriauno: String
    private lateinit var galeriados: String
    private lateinit var galeriatres: String
    private lateinit var nombre: String
    private lateinit var resena: String
    private lateinit var direccion: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paso_irestaurant_details)

        if(savedInstanceState == null){
            val bundle = intent.extras

            nombre = if (bundle != null){bundle.getString("nombre","nombre")}else{savedInstanceState?.getSerializable("nombre") as String}
            id = if (bundle != null){bundle.getString("id","id")}else{savedInstanceState?.getSerializable("id") as String}
            calificacion = if (bundle != null){bundle.getString("calificacion","calificacion")}else{savedInstanceState?.getSerializable("calificacion") as String}
            costo = if (bundle != null){bundle.getString("costo","costo")}else{savedInstanceState?.getSerializable("costo") as String}
            foto = if (bundle != null){bundle.getString("foto","foto")}else{savedInstanceState?.getSerializable("foto") as String}
            fundacion = if (bundle != null){bundle.getString("fundacion","fundacion")}else{savedInstanceState?.getSerializable("fundacion") as String}
            resena = if (bundle != null){bundle.getString("resena","resena")}else{savedInstanceState?.getSerializable("resena") as String}
            galeriauno = if (bundle != null){bundle.getString("galeriauno","galeriauno")}else{savedInstanceState?.getSerializable("galeriauno") as String}
            galeriados = if (bundle != null){bundle.getString("galeriados","galeriados")}else{savedInstanceState?.getSerializable("galeriados") as String}
            galeriatres = if (bundle != null){bundle.getString("galeriatres","galeriatres")}else{savedInstanceState?.getSerializable("galeriatres") as String}
            direccion = if (bundle != null){bundle.getString("direccion","direccion")}else{savedInstanceState?.getSerializable("direccion") as String}
        }

        supportActionBar?.title="Detalle"
        var ivItem = findViewById<ImageView>(R.id.ivPicture)
        Picasso.get().load(foto).into(ivItem)
        findViewById<TextView>(R.id.tvNombre).text = nombre.toString()
        findViewById<TextView>(R.id.tvDireccion).text = direccion.toString()
        findViewById<TextView>(R.id.resena).text = resena.toString()

        var ivSecond = findViewById<ImageView>(R.id.ivItem1)
        Picasso.get().load(galeriauno).into(ivSecond)

        var ivThird = findViewById<ImageView>(R.id.ivItem3)
        Picasso.get().load(galeriados).into(ivThird)

        var ivFourth = findViewById<ImageView>(R.id.ivItem4)
        Picasso.get().load(galeriatres).into(ivFourth)


    }
}