package com.example.androidrecuperacionjunio2.Model

data class Articulo(
    var id: String="",
    var nombre:String="",
    var precio: Double=0.0,
    var oferta: Boolean=false,
    var stock: Int=0,
    var imagen: String=""
)