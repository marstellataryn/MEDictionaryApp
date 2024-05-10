package com.example.medictionaryapp.ui.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.medictionaryapp.dataSource.Medication

class MedicationViewModel : ViewModel() {
    private val _medications = MutableLiveData<List<Medication>>()
    val medications: LiveData<List<Medication>> = _medications

    init {
        // Initialize with an empty list
        _medications.value = emptyList()
    }

    fun addMedication(medication: Medication) {
        val currentList = _medications.value ?: emptyList()
        _medications.value = currentList + medication
    }
}
