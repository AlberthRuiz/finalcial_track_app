package edu.pe.cibertec.financial_track_app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TranasaccionDao{
    @Query("select * from transaactions ORDER BY date DESC")
    fun getAllTransactions(): Flow<List<Transaccion>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(transaccion: Transaccion)

    @Delete
    fun deleteTransaction(transaccion: Transaccion)

}