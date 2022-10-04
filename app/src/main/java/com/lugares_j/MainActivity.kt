package com.lugares_j

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lugares_j.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    //Definición del objeto para hacer la autenticación
    private lateinit var auth : FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth =  Firebase.auth

        binding.btRegister.setOnClickListener { haceRegistro() }
        binding.btLogin.setOnClickListener { haceLogin() }
    }

    private fun haceRegistro() {
        //crea variables para guardarlas de los campos donde se ingresa en los inputs
        val email = binding.etEmail.text.toString()
        val clave = binding.etClave.text.toString()

        //Se llama a la función para crear un usuario
        auth.createUserWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this) { task ->

                //si se pudo crear el usuario
                var user: FirebaseUser? = null
                if (task.isSuccessful) {
                    Log.d("Autenticando", "usuario creado")
                    user = auth.currentUser //se recupera la info del usuario creado

                } else {
                    Log.d("Autenticando", "Ha ocurrido un error al crear el usuario")
                }

                actualiza(user)
            }
    }

    private fun haceLogin() {

        //crea variables para guardarlas de los campos donde se ingresa en los inputs
        val email = binding.etEmail.text.toString()
        val clave = binding.etClave.text.toString()

        //Se llama a la función para crear un usuario
        auth.signInWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this) { task ->

                //si se pudo crear el usuario
                var user: FirebaseUser? = null
                if (task.isSuccessful) {
                    Log.d("Autenticando", "usuario creado")
                    user = auth.currentUser //se recupera la info del usuario creado

                } else {
                    Log.d("Autenticando", "Ha ocurrido un error al crear el usuario")
                }

                actualiza(user)
            }

    }

    private fun actualiza(user: FirebaseUser?) {

        //si exist el usuario, se manda para el main page (Activity)
        if (user != null) {
            //se manda a la otra pantalla
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }
    }

    //para ingresar de forma directa a la hora de ya tener el usuario creado y no tener que volver a ponerlo
    //se ejecuta cuando el app aparezca en la pantalla
    public override fun onStart() {
        super.onStart()
        val usuario = auth.currentUser
        actualiza(usuario)
    }
}