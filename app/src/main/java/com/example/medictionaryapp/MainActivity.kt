package com.example.medictionaryapp


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medictionaryapp.ui.theme.MedicationViewModel
import com.example.medictionaryapp.ui.theme.MyTheme
import com.example.medictionaryapp.ui.theme.PreferencesActivity
import com.example.medictionaryapp.ui.theme.screens.AddMedicationScreen
import com.example.medictionaryapp.ui.theme.screens.HelpScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                val navController = rememberNavController()
                val viewModel: MedicationViewModel = remember { MedicationViewModel() }
                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        MainScreen(navController, viewModel)
                    }
                    composable("addMedication") {
                        AddMedicationScreen(navController, viewModel)
                    }
                    composable("help") {
                        HelpScreen()
                    }
                }
            }
        }
    }
}


@Composable
fun MEDictionaryApp() {
    val navController = rememberNavController()
    val viewModel: MedicationViewModel = remember { MedicationViewModel() } // Create the ViewModel

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController = navController, viewModel = viewModel) // Pass the ViewModel to MainScreen
        }
        composable("help") {
            HelpScreen()
        }
        composable("addMedication") {
            AddMedicationScreen(navController = navController, viewModel = viewModel) // Pass the ViewModel to AddMedicationScreen
        }
    }

}



@Composable
fun MainScreen(navController: NavController, viewModel: MedicationViewModel) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp), // Add horizontal padding for the buttons
            verticalArrangement = Arrangement.Center, // Center content vertically
        ) {
            // Row containing MEDictionary text, Help button, and Settings button
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                // Help button on the left
                Button(
                    onClick = { navController.navigate("help") }
                ) {
                    Text("Help")
                }

                // MEDictionary text
                Text(
                    text = "MEDictionary",
                    style = MaterialTheme.typography.headlineMedium,
                )

                // Settings button on the right
                Button(
                    onClick = { navigateToPreferences(navController.context) }
                ) {
                    Text("Settings")
                }
            }

            Spacer(modifier = Modifier.height(16.dp)) // Add space between the buttons and the "Add Medication" button

            // Button to navigate to Add Medication screen
            Button(
                onClick = { navController.navigate("addMedication") },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Add Medication")
            }

            // List of medications (in LazyColumn)
            LazyColumn(
                modifier = Modifier
                    .weight(1f) // Fill the available space between the buttons and the "Add Medication" button
            ) {
                items(viewModel.medications.value ?: emptyList()) { medication ->
                    MedicationItem(name = medication.name, disease = medication.disease)
                }
            }
        }
    }
}

// Function to navigate to PreferencesActivity
private fun navigateToPreferences(context: Context) {
    val intent = Intent(context, PreferencesActivity::class.java)
    context.startActivity(intent)
}

@Composable
fun MedicationItem(name: String, disease: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = name, style = MaterialTheme.typography.titleSmall)
        Text(text = disease, style = MaterialTheme.typography.labelSmall)
    }
}

@Preview
@Composable
fun MEDictionaryAppPreview() {
    MyTheme {
        MEDictionaryApp ()
    }
}

