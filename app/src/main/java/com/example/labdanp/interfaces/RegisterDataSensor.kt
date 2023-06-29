package com.example.labdanp.interfaces

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.labdanp.CustomTopAppBar
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterDataSensor(navController: NavController) {

    val calendarState = rememberSheetState()

    Scaffold(
        topBar = { CustomTopAppBar(
            navController = navController,
            title = "Registro de datos Sensor",
            showBackIcon = true
        )
        },
        content = {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                val comentario = remember {
                    mutableStateOf("")
                }

                val fecha = remember {
                    mutableStateOf("")
                }

                val datoSensor = remember {
                    mutableStateOf("")
                }

                CalendarDialog(
                    state = calendarState,
                    selection = CalendarSelection.Date { date ->
                        fecha.value = "$date"
                    }
                )

                Spacer(modifier = Modifier.height(100.dp))

                Text(
                    text = "Registro de datos de Sensor",
                    style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive)
                )

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextField(
                        modifier = Modifier.width(174.dp),
                        label = { Text(text = "Fecha") },
                        value = fecha.value,
                        onValueChange = { fecha.value = it }
                    )
                    Button(
                        onClick = { calendarState.show() },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .padding(15.dp, 0.dp, 0.dp, 0.dp)
                            .height(40.dp)
                            .width(90.dp)

                    ) {
                        Text(text = "Ver")
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))

                TextField(
                    label = { Text(text = "Dato del Sensor") },
                    value = datoSensor.value,
                    onValueChange = { datoSensor.value = it }
                )

                Spacer(modifier = Modifier.height(15.dp))

                TextField(
                    label = { Text(text = "Comentario") },
                    value = comentario.value,
                    onValueChange = { comentario.value = it }
                )

                Spacer(modifier = Modifier.height(30.dp))

                var show by rememberSaveable {
                    mutableStateOf(false)
                }

                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {
                            show = true
                        },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "Registrar dato Sensor")
                    }
                }

                DialogConfirm(show, {show = false}, {Log.i("accion", "click")})
            }
        }
    )
}

@Composable
fun DialogConfirm(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if(show) {
        AlertDialog(onDismissRequest = { onDismiss() },
            confirmButton = { TextButton(onClick = { onConfirm() }) {
                Text(text = "Aceptar")
            }},
            dismissButton = { TextButton(onClick = { onDismiss() }) {
                Text(text = "Cancelar")
            }},
            title = { Text(text = "CONFIRMACION DE REGISTRO") },
            text = { Text(text = "¿Estas seguro de registrar esta dataSensor?")})
    }
}