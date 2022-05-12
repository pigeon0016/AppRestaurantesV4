package com.example.apprestaurantesv4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.apprestaurantesv4.databinding.ActivityPasoAloginBinding
import com.google.firebase.auth.FirebaseAuth
import java.security.AccessController.getContext

class PasoALogin : AppCompatActivity() {
   private lateinit var binding: ActivityPasoAloginBinding // se hace el bidning para el layout de log in
    lateinit var autenticacion: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPasoAloginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        autenticacion = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener{
            logeo() }

        binding.btnRegistrar.setOnClickListener{
            registrate() }
    }


    private fun registrate() {
        val intent = Intent(this,PasoBRegistro::class.java)
        this.startActivity(intent)
    }


    private fun logeo() {
        val correo: String = binding.etUser.text.toString()
        val password: String = binding.etPwd.text.toString()

        autenticacion.signInWithEmailAndPassword(correo,password).addOnCompleteListener{
            if(it.isSuccessful){
                // si la contraseña y el password son correctos se va a la activity principal donde esta el recyucler view
                val intent = Intent(this,MainActivity::class.java)
                this.startActivity(intent)
            }else{
                Toast.makeText(this,"USuario y contraseña incorrecta o debes darte de alta", Toast.LENGTH_LONG).show()
            }
        }
    }



}