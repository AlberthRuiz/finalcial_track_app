package edu.pe.cibertec.financial_track_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider


import edu.pe.cibertec.financial_track_app.data.DatabaseProvider
import edu.pe.cibertec.financial_track_app.data.Transaccion
import edu.pe.cibertec.financial_track_app.repository.TransaccionRepository
import edu.pe.cibertec.financial_track_app.screens.AddTransaccionesDialog
import edu.pe.cibertec.financial_track_app.ui.theme.Financial_track_appTheme
import edu.pe.cibertec.financial_track_app.viewmodel.TransaccionViewModel
import edu.pe.cibertec.financial_track_app.viewmodel.TransaccionViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db = DatabaseProvider.getDatabase(applicationContext)
        val repository = TransaccionRepository(db.transaccionDao())
        val factory = TransaccionViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory)[TransaccionViewModel::class.java]

        setContent {
            Financial_track_appTheme {
                MainScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: TransaccionViewModel) {
    val transaccciones by viewModel.trnasaacciones.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var transaccionEditar by remember { mutableStateOf<Transaccion?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("APP Trackeo Gastos") })

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true }
            ) {
                Text("+")

            }
        }
    ) { paddingVal ->

        Column(
            modifier = Modifier
                .padding(paddingVal)
                .fillMaxSize()
        ) {
            LazyColumn {
                items(transaccciones) { transaccion ->
                    TransaccionItem(
                        transaccion = transaccion,
                        onDelete = { viewModel.delete(it) },
                        onClick = { transaccionEditar = it }
                    )

                }
            }
        }

        if (showDialog) {
            AddTransaccionesDialog(
                onAdd = { transaccion ->
                    viewModel.insert(transaccion)
                    showDialog = false
                },
                onDismiss = {
                    showDialog = false
                }
            )
        }
        if (transaccionEditar != null) {
            AddTransaccionesDialog(
                transaccion =  transaccionEditar,
                onAdd = { UpdateTransaccion ->
                    viewModel.insert(UpdateTransaccion)
                    transaccionEditar = null
                },
                onDismiss = {
                    transaccionEditar = null
                }
            )
        }

    }

}

@Composable
fun TransaccionItem(
    transaccion: Transaccion,
    onDelete: (Transaccion) -> Unit,
    onClick: (Transaccion) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick(transaccion)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = transaccion.description)
                Text(text = "${if (transaccion.type == "Ingreso") "+" else "-"}S/ ${transaccion.amount}")
                Text(
                    text = "Fecha: ${
                        java.text.SimpleDateFormat("yyyy-MM-dd")
                            .format(java.util.Date(transaccion.date))
                    }"
                )
            }
            IconButton(onClick = { onDelete(transaccion) }) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
            }
        }
    }

}