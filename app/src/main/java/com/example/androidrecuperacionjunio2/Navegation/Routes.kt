package com.example.androidrecuperacionjunio2.Navegation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes: NavKey {
    @Serializable
    data object LoginScreen: Routes()
    @Serializable
    data object HomeScreen: Routes()
    @Serializable
    data object NuevoArticuloScreen: Routes()
    @Serializable
    data object Error : Routes()
}