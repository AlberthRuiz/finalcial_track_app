package edu.pe.cibertec.financial_track_app.data

import android.content.Context
import androidx.room.Room


object DatabaseProvider{

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase{
        return  INSTANCE?: synchronized (this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "finantial_tracker_db"
            ).build()
            INSTANCE = instance
            instance
        }
    }

}