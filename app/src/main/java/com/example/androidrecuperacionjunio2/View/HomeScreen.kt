package com.example.androidrecuperacionjunio2.View

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidrecuperacionjunio2.Model.Articulo
import com.example.androidrecuperacionjunio2.ViewModel.ArticuloViewModel

@Composable
fun HomeScreen(
    onNavigateToNuevo: () -> Unit,
    onLogout: () -> Unit,
    vm: ArticuloViewModel = viewModel()
)
{
    val state by vm.uiState.collectAsState()
    val articulos by vm.articulos.collectAsState()

    Scaffold(
        bottomBar = {
            Button(
                onClick = { onNavigateToNuevo() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
            ) {
                Text("Agregar jugador", color = Color.White)
            }
        }
    ) { pad ->
        Column(
            modifier = Modifier
                .padding(pad)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Plantilla temporada 25/26",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onLogout) {
                    Icon(Icons.Default.ExitToApp, contentDescription = "Cerrar Sesión")
                }
            }

            if (state.estaCargando) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(articulos) { articulo ->
                        ArticuloItem(articulo, onDelete = { vm.eliminarArticulo(articulo.id) })
                    }
                }
            }
        }
    }
}
@Composable
fun ArticuloItem(articulo: Articulo,onDelete:()->Unit){
   Card(
       modifier = Modifier
           .fillMaxWidth()
   ) {

   }
}