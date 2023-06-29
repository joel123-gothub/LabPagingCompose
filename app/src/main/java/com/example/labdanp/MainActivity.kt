package com.example.labdanp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.labdanp.dataRepository.DataRepository
import com.example.labdanp.interfaz.DataScreen
import com.example.labdanp.ui.theme.LabDANPTheme
import com.example.labdanp.paging.DataViewModel
import com.example.labdanp.paging.DataViewModelFactory

class MainActivity : ComponentActivity() {
    private val viewModel: DataViewModel by viewModels { DataViewModelFactory(DataRepository(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabDANPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DataScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LabDANPTheme {
        Greeting("Android")
    }
}