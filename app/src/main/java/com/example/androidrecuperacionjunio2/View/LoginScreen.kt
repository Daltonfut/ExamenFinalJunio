package com.example.androidrecuperacionjunio2.View

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidrecuperacionjunio2.R
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(auth: FirebaseAuth,onLoginOk:()-> Unit) {
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var error by rememberSaveable {
        mutableStateOf(false)
    }
    var mostrarpassword by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.
        fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null, modifier = Modifier
                .size(140.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text("Inicia sesión", fontSize = 30.sp)

        Spacer(modifier= Modifier.height(20.dp))

        TextField(
            value = email,
            onValueChange = {
                email=it
            },
            label = {
                Text("Email")
            }
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = password,
            onValueChange = {
                password=it
            },
            label = {
                Text("Contraseña")
            },
            visualTransformation = if (mostrarpassword) VisualTransformation.None else
                PasswordVisualTransformation(),
            trailingIcon = {
                Text(
                    text = if (mostrarpassword) "Ocultar" else "Mostrar",
                    modifier = Modifier.
                    clickable{
                        mostrarpassword=!mostrarpassword
                    }
                )
            }
        )
        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                auth.signInWithEmailAndPassword(email,password)
                    .addOnSuccessListener {
                        user ->
                        onLoginOk()
                    }
                    .addOnFailureListener {
                        e->
                        Log.e("Firebase","error ${e.message}")
                        error=true
                    }
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFF00489a))
        ) {
            Text("Login")
        }
        if (error){
            AlertDialog(
                onDismissRequest = {
                    error=false
                },
                confirmButton = {
                    Button(
                        onClick = {
                            error=false
                        }
                    ) {
                        Text("Aceptar")
                    }
                },
                title ={
                    Text("Login")
                },
                text = {
                    Text("Usuario o contraseña incorrectos")
                }
            )
        }
    }
}
