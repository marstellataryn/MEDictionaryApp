package com.example.medictionaryapp.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medictionaryapp.dataSource.Medication

@Composable
fun MedicationDetailsScreen(medication: Medication) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${medication.name}", style = MaterialTheme.typography.titleSmall)
            Text(text = "Disease: ${medication.disease}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview
@Composable
fun PreviewMedicationDetailsScreen() {
    val medication = Medication("Lisinopril", "Hypertension")
    MedicationDetailsScreen(medication = medication)
}