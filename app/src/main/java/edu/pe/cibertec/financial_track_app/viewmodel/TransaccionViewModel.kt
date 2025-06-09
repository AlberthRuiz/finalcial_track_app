package edu.pe.cibertec.financial_track_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import edu.pe.cibertec.financial_track_app.data.Transaccion
import edu.pe.cibertec.financial_track_app.data.TransaccionRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TransaccionViewModel (
    val repository: TransaccionRepository
): ViewModel(){
    val trnasaacciones : StateFlow<List<Transaccion>> = repository.getAllTrnsaccitions()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun insert(transaccion: Transaccion){
        viewModelScope.launch {
            repository.insertTransaccion(transaccion)
        }
    }

    fun delete(transaccion: Transaccion){
        viewModelScope.launch {
            repository.deleteTransaccion(transaccion)
        }
    }
}

class TransaccionViewModelFactory(val repository: TransaccionRepository):
    ViewModelProvider.Factory{
    override fun<T: ViewModel> create (modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(TransaccionViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return TransaccionViewModel(repository) as T
        }
        throw IllegalArgumentException("View model desconocido")
    }

}