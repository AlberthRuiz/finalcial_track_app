package edu.pe.cibertec.financial_track_app.data

import kotlinx.coroutines.flow.Flow

class TransaccionRepository (private  val dao: TranasaccionDao) {
    fun getAllTrnsaccitions(): Flow<List<Transaccion>> = dao.getAllTransactions()
    fun insertTransaccion(transaccion: Transaccion) = dao.insertTransaction(transaccion )
    fun deleteTransaccion(transaccion: Transaccion) = dao.deleteTransaction(transaccion)
}