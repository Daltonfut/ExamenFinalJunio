package com.example.androidrecuperacionjunio2.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.androidrecuperacionjunio2.Model.Articulo
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ArticuloUiState(
    var estaCargando: Boolean=false
)
class ArticuloViewModel: ViewModel() {
    private val db = Firebase.firestore
    private val articulosCollection = db.collection("Articulos")

    private val _uiState = MutableStateFlow(ArticuloUiState())
    val uiState: StateFlow<ArticuloUiState> = _uiState.asStateFlow()

    private val _lista = MutableStateFlow<List<Articulo>>(emptyList())
    val articulos: StateFlow<List<Articulo>> = _lista

    private fun leerArticulo(){
        _uiState.update { it.copy(estaCargando = true) }
        articulosCollection.addSnapshotListener {
            snapshot, error ->
            if (error!=null){
                _uiState.update { it.copy(estaCargando = false) }
                return@addSnapshotListener
            }
            if (snapshot!=null){
                val articulosList = snapshot.documents.mapNotNull { doc->

                    val articulos = doc.toObject(Articulo::class.java)
                    articulos?.id=doc.id
                    articulos
                }
                _lista.value=articulosList
                _uiState.update { it.copy(estaCargando = false) }
            }

        }
    }
    init {
        leerArticulo()
    }
    fun agregarArticulo(
        nombre: String,
        precio: Double,
        oferta: Boolean,
        stock: Int,
        imagen: String,
        onSucess:()-> Unit
    ){
        val nuevoArticuloLista = Articulo(
            nombre=nombre,
            precio = precio,
            oferta = oferta,
            stock = stock,
            imagen = imagen
        )
        articulosCollection.add(nuevoArticuloLista)
            .addOnSuccessListener {
                onSucess()
            }
            .addOnFailureListener {
                e->
                Log.e("Firestore"," Error ${e.message}")
            }
    }
    fun eliminarArticulo(id: String){
        articulosCollection.document(id).delete()
            .addOnSuccessListener {
                Log.d("Firestore"," Articulo eliminado correctamente")
            }
            .addOnFailureListener {
                e->
                Log.e("Firestore"," Error al eliminar el articulo ${e.message}")
            }
    }
}