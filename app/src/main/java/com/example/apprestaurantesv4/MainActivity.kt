package com.example.apprestaurantesv4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apprestaurantesv4.databinding.ActivityMainBinding
import com.example.apprestaurantesv4.databinding.ActivityPasoAloginBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), PasoGRestaurantAdapter.OnItemListener{
    private lateinit var binding: ActivityMainBinding
    lateinit var autenticacion: FirebaseAuth
    private val imagenesres = ArrayList<PasoFRestaurantes>() // Del recyler
    private lateinit var adapter : PasoGRestaurantAdapter  // Del recyler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//++++++++++++++++++++++++++++++++++++++Retrofit
        getAllRestaurants()
        initRecyclerView()
//++++++++++++++++++++++++++++++++++++++Retrofit
    }

    //++++++++++++++++++++++++++++++++++++++Recylerview
    private fun initRecyclerView() {
        adapter = PasoGRestaurantAdapter(imagenesres,this)
        binding.rvRestaurants.layoutManager = LinearLayoutManager(this)
        binding.rvRestaurants.adapter = adapter
    }


    //********************************************* Parte de los restaurantes
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://demo5556878.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    private fun getAllRestaurants() {
       CoroutineScope(Dispatchers.IO).launch {
           val call: Response<PasoERestaurantResponse> = getRetrofit().create(PasoCAPIService::class.java).getAllRestaurants("allrestarants")
           val allRestaurants:PasoERestaurantResponse? = call.body()

           runOnUiThread{
               if(call.isSuccessful){
                   var rest: ArrayList<PasoFRestaurantes> = (allRestaurants?.restaurantes ?: emptyArray<PasoERestaurantResponse>()) as ArrayList<PasoFRestaurantes>
                   imagenesres.clear()
                   imagenesres.addAll(rest)
                   adapter.notifyDataSetChanged()
               }
           }
       }
    }


    override fun clickRestaurant(resta: PasoFRestaurantes) {
        val intent = Intent(this,PasoIRestaurantDetails::class.java)
        intent.putExtra("id",resta.id)
        intent.putExtra("calificacion",resta.calificacion)
        intent.putExtra("costo",resta.costo)
        intent.putExtra("foto",resta.foto)
        intent.putExtra("fundacion",resta.fundacion)
        intent.putExtra("galeriauno",resta.galeria?.get(0)?.toString())
        intent.putExtra("galeriados",resta.galeria?.get(2)?.toString())
        intent.putExtra("galeriatres",resta.galeria?.get(1)?.toString())
        intent.putExtra("nombre",resta.nombre)
        intent.putExtra("resena",resta.resena)
        intent.putExtra("direccion",resta.direccion)
        startActivity(intent)
    }


    ///////////////////////////////////////////////////
    //boton para salir de la pagina
    fun aprtieta(view: View) {
       autenticacion.signOut()
        val intent = Intent(this,PasoALogin::class.java)
        this.startActivity(intent)
    }


}