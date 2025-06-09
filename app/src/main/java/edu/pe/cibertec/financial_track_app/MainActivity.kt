package edu.pe.cibertec.financial_track_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import edu.pe.cibertec.financial_track_app.data.DatabaseProvider
import edu.pe.cibertec.financial_track_app.data.TransaccionRepository
import edu.pe.cibertec.financial_track_app.ui.theme.Financial_track_appTheme
import edu.pe.cibertec.financial_track_app.viewmodel.TransaccionViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val db = DatabaseProvider.getDatabase(applicationContext)
            val repository = TransaccionRepository(db.TranasaccionDao())

            val factory = TransaccionViewModel(repository)
            //val viewModel = ViewModelProvider(this,factory)[TransaccionViewModel::class.java]
            Financial_track_appTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(){
    Text("Hola Mundo ")


}