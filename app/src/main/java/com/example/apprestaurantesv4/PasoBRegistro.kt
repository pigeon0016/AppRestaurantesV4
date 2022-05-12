package com.example.apprestaurantesv4

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.apprestaurantesv4.databinding.ActivityPasoAloginBinding
import com.example.apprestaurantesv4.databinding.ActivityPasoBregistroBinding
import com.google.firebase.auth.FirebaseAuth

class PasoBRegistro : AppCompatActivity() {
    private lateinit var binding: ActivityPasoBregistroBinding
    lateinit var autenticacion: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasoBregistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        autenticacion = FirebaseAuth.getInstance()

        binding.btnAceptarRegister.setOnClickListener{
        registro()
        }

        binding.btnIniciarSesionRegister.setOnClickListener{
            irlogeo()
        }

    }

    private fun irlogeo() {
        val intent = Intent(this,PasoALogin::class.java)
        this.startActivity(intent)
    }

    private fun registro() {
       val correo: String = binding.etEmailRegister.text.toString()
        val password: String = binding.etPswRegister.text.toString()
        autenticacion.createUserWithEmailAndPassword(correo,password).addOnCompleteListener{
            if(it.isSuccessful){
                //vamos a la pantalla del recycler
                val intent = Intent(this,MainActivity::class.java)
                this.startActivity(intent)
            } else{
                Toast.makeText(this,"intentalo de nuevo", Toast.LENGTH_LONG).show()
            }
        }
    }
}