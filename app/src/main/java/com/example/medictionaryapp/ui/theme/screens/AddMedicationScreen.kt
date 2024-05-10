package com.example.medictionaryapp.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.medictionaryapp.ui.theme.MedicationViewModel


data class Medication(val name: String, val usage: String)


@Composable
fun AddMedicationScreen(navController: NavController, viewModel: MedicationViewModel) {
    var medicationName by rememberSaveable { mutableStateOf("") }
    var medicationUsage by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Add Medication",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Medication Name field
        TextField(
            value = medicationName,
            onValueChange = { medicationName = it },
            label = { Text("Medication Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Medication Usage field
        TextField(
            value = medicationUsage,
            onValueChange = { medicationUsage = it },
            label = { Text("Medication Usage") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val newMedication = com.example.medictionaryapp.dataSource.Medication(medicationName, medicationUsage)
                viewModel.addMedication(newMedication)
                navController.popBackStack()
            }
        ) {
            Text(text = "Save")
        }
    }
}

@Preview
@Composable
fun PreviewAddMedicationScreen() {
    MaterialTheme {
        val navController = rememberNavController()
        val fakeViewModel = MedicationViewModel() // Create a fake ViewModel for preview
        AddMedicationScreen(navController = navController, viewModel = fakeViewModel)
    }
}

