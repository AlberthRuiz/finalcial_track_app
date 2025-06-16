package edu.pe.cibertec.financial_track_app.repository

import edu.pe.cibertec.financial_track_app.data.TranasaccionDao
import edu.pe.cibertec.financial_track_app.data.Transaccion
import kotlinx.coroutines.flow.Flow

class TransaccionRepository(private val dao: TranasaccionDao) {
    fun getAllTransacctions(): Flow<List<Transaccion>> = dao.getAll()

    suspend fun insertTransaccion(transaccion: Transaccion) = dao.insert(transaccion)

    suspend fun deleteTransaccion(transaccion: Transaccion) = dao.delete(transaccion)
}