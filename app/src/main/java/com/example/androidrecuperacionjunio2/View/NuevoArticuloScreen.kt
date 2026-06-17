package com.example.androidrecuperacionjunio2.View

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NuevoArticuloScreen() {
    var nombre by rememberSaveable {
        mutableStateOf("")
    }
    var precio by rememberSaveable {
        mutableStateOf("")
    }
    var imagen by rememberSaveable {
        mutableStateOf("")
    }
    var stock by rememberSaveable {
        mutableStateOf("")
    }
    var oferta by rememberSaveable {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Artículo nuevo", fontSize = 30.sp,
            fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = nombre,
            onValueChange = {
                nombre=it
            },
            label = {
                Text("Nombre")
            }, modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = precio,
            onValueChange = {
                precio=it
            },
            label = {
                Text("Precio")
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = imagen,
            onValueChange = {
                imagen=it
            },
            label = {
                Text("URL imagen")
            }, modifier = Modifier.
            fillMaxWidth()
                .height(120.dp)
        )
        Spacer(modifier = Modifier
            .height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .clickable{

                    }
            )
            Spacer(modifier = Modifier
                .width(30.dp))
            Text("En oferta")
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier=Modifier.fillMaxWidth()
                .padding(10.dp)
        ) {
            Button(
                onClick = {

                }
            ) {
                Text("Agregar Artículo")
            }

            Spacer(modifier = Modifier.width(30.dp))
            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors()
            ) {
                Text("Cancelar")
            }
        }

    }
}
@Preview(showBackground = true)
@Composable
fun PreviewArticulo(){
    NuevoArticuloScreen()
}