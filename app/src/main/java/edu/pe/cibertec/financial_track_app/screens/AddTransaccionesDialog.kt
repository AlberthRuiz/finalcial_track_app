package edu.pe.cibertec.financial_track_app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import edu.pe.cibertec.financial_track_app.data.Transaccion

@Composable
fun AddTransaccionesDialog(
    onAdd: (Transaccion) -> Unit,
    onDismiss: () -> Unit,
    transaccion: Transaccion? = null
) {
    var amount by remember { mutableStateOf(transaccion?.amount?.toString() ?: "0") }
    var description by remember { mutableStateOf(transaccion?.description?.toString() ?: "") }
    var type by remember { mutableStateOf(transaccion?.type?.toString() ?: "Ingreso") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (transaccion == null) "Nueva Trasaccion" else "Editar Trsnaccion") },
        text = {
            Column {
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Monto S/. ") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(Modifier.height(10.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descripcion: ") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(10.dp))
                Row {
                    RadioButton(
                        selected = type == "Ingreso",
                        onClick = { type = "Ingreso" }
                    )
                    Text("Ingreso")
                    RadioButton(
                        selected = type == "Gasto",
                        onClick = { type = "Gasto" }
                    )
                    Text("Gasto")
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val amountValue = amount.toDoubleOrNull() ?: 0.0
                    if (amountValue > 0 && description.isNotBlank()) {
                        onAdd(
                            Transaccion(
                                id = transaccion?.id ?: 0,
                                amount = amountValue,
                                description = description,
                                type = type,
                                date = System.currentTimeMillis()
                            )
                        )
                    }
                }
            ) {
                Text("Guardar")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cerrar")
            }
        }


    )


}