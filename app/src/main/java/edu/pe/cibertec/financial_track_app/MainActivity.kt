package edu.pe.cibertec.financial_track_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import edu.pe.cibertec.financial_track_app.data.DatabaseProvider
import edu.pe.cibertec.financial_track_app.data.Transaccion
import edu.pe.cibertec.financial_track_app.data.TransaccionRepository
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
    val transaccciones  by viewModel.trnasaacciones.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var transaccionEditar by remember { mutableStateOf<Transaccion?>(null)}

    Scaffold (topBar = {
        TopAppBar( title =  { Text("APP Trackeo Gastos") })

    },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {showDialog= true}

            ) {
                Text("+")

            }
        }
        ) {
        paddingVal ->
         @androidx.compose.runtime.Composable {
            Column (modifier = Modifier.padding(paddingVal).fillMaxSize()
            ) {
                Text("Hola mundo")
            }
        }
    }






}