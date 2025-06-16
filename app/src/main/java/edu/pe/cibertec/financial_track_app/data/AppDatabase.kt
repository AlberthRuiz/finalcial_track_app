package edu.pe.cibertec.financial_track_app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.pe.cibertec.financial_track_app.data.Transaccion

@Database(entities = [Transaccion::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){

    abstract fun transaccionDao(): TranasaccionDao
}