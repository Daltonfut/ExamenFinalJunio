package com.example.androidrecuperacionjunio2.Navegation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.androidrecuperacionjunio2.View.HomeScreen
import com.example.androidrecuperacionjunio2.View.LoginScreen
import com.example.androidrecuperacionjunio2.View.NuevoArticuloScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun GestionNavegacion(auth: FirebaseAuth) {
    val pilaNavegacion = rememberNavBackStack(Routes.LoginScreen)

    NavDisplay(
        backStack = pilaNavegacion,
        onBack = {
            if (pilaNavegacion.size>1){
                pilaNavegacion.removeLastOrNull()
            }
        },
        entryProvider=entryProvider {
            entry<Routes.LoginScreen>{
                LoginScreen(
                    auth=auth,
                    onLoginOk = {
                        pilaNavegacion.add(Routes.HomeScreen)
                    }
                )
            }
            entry<Routes.HomeScreen>{
                HomeScreen()
            }
            entry<Routes.NuevoArticuloScreen>{
                NuevoArticuloScreen()
            }
            entry<Routes.Error>{
                Error(" Se ha producido un error")
            }
        }
    )
}