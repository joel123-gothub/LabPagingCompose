package com.example.labdanp.interfaz

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.labdanp.datos.Data
import com.example.labdanp.paging.DataViewModel
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.labdanp.ui.theme.PurpleProfund

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DataScreen(viewModel: DataViewModel) {
    val data: LazyPagingItems<Data> = viewModel.data.collectAsLazyPagingItems()

    LazyVerticalGrid(cells = GridCells.Fixed(1)) {
        items(data.itemCount) { index ->
            val item = data[index]
            if (item != null) {
                DataItem(data = item)
            } else if (data.loadState.append.endOfPaginationReached && index == data.itemCount - 1) {
                Text(
                    text = "No hay más datos disponibles",
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        when (data.loadState.append) {
            is LoadState.Loading -> {
                println("Cargando datos...")
                item {
                    Box(modifier = Modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            }
            is LoadState.Error -> {
                val errorMessage = (data.loadState.append as LoadState.Error).error.message
                item {
                    Box(modifier = Modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
                        Text(text = "Error al cargar los datos: $errorMessage", textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}
@Composable
fun DataItem(data: Data) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Fecha: ${data.fecha}")
        Text(text = "Dato X: ${data.datoX}")
        Text(text = "Comentario: ${data.comentario}")
    }
}
/*
@Composable
fun DataItem(data: Data) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .aspectRatio(5f)
            .clip(RoundedCornerShape(30.dp)),
        backgroundColor = PurpleProfund,
        elevation = 10.dp,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Fecha: ${data.fecha}",
                    style = TextStyle(color = Color.White))

                Spacer(modifier = Modifier.weight(5f))

                Text(text = "Dato Sensor: ${data.datoX}",
                    style = TextStyle(color = Color.White))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Comentario: ${data.comentario}",
                    style = TextStyle(color = Color.White)
                )
            }
        }
    }
}*/
