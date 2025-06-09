package edu.pe.cibertec.financial_track_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import edu.pe.cibertec.financial_track_app.data.DatabaseProvider
import edu.pe.cibertec.financial_track_app.ui.theme.Financial_track_appTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db = DatabaseProvider.getDatabase(applicationContext)

        setContent {

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