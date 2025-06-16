package edu.pe.cibertec.financial_track_app.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TranasaccionDao{
    @Query("select * from TRANSACCIONES ORDER BY date DESC")
    fun getAll(): Flow<List<Transaccion>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaccion: Transaccion)

    @Delete
    suspend fun  delete(transaccion: Transaccion)

}