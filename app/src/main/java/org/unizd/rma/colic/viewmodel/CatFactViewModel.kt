package org.unizd.rma.colic.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.unizd.rma.colic.model.CatFact
import org.unizd.rma.colic.repository.CatFactRepository

class CatFactViewModel : ViewModel() {

    private val repository = CatFactRepository()

    private val _facts = MutableLiveData<List<CatFact>>()
    val facts: LiveData<List<CatFact>> = _facts

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    init {
        fetchCatFacts()
    }

    private fun fetchCatFacts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getCatFacts()
                _facts.value = response.data
            } catch (e: Exception) {
                println("API call failed: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}
