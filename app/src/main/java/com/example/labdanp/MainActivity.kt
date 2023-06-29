package com.example.labdanp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.labdanp.dataRepository.DataRepository
import com.example.labdanp.interfaces.DataScreen
import com.example.labdanp.interfaces.ForgotPassword.ForgotPassword
import com.example.labdanp.interfaces.Login.LoginPage
import com.example.labdanp.interfaces.PrincipalMenu
import com.example.labdanp.interfaces.RegisterDataSensor
import com.example.labdanp.ui.theme.LabDANPTheme
import com.example.labdanp.paging.DataViewModel
import com.example.labdanp.paging.DataViewModelFactory

class MainActivity : ComponentActivity() {
    private val viewModel: DataViewModel by viewModels { DataViewModelFactory(DataRepository(this)) }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabDANPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenMain(viewModel)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenMain(viewModel: DataViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginPage(navController) }
        composable("menu") { PrincipalMenu(navController = navController) }
        composable("forgot-password") { ForgotPassword(navController = navController) }
        composable("registrar-datoSensor") { RegisterDataSensor(navController = navController) }
        composable("listado-datos") { DataScreen(viewModel) }
    }
}
