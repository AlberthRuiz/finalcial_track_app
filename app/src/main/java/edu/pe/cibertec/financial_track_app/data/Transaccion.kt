package edu.pe.cibertec.financial_track_app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TRANSACCIONES")
data class Transaccion(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: Double,
    val description: String,
    val type: String,
    val date: Long
)