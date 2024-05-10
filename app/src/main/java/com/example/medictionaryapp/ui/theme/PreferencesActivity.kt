package com.example.medictionaryapp.ui.theme

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class UserPreferences(
    var fontSize: Int,
    var isDarkMode: Boolean
)

class PreferencesActivity : ComponentActivity() {
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyTheme(darkTheme = userPreferences.isDarkMode) {
                userPreferences = getPreferences()
            }
                PreferencesScreen(userPreferences, onSavePreferences = ::savePreferences)
        }
    }

     private fun savePreferences(userPreferences: UserPreferences) {
        val sharedPreferences: SharedPreferences = getSharedPreferences(
            "MyPrefs",
            Context.MODE_PRIVATE
        )
        with(sharedPreferences.edit()) {
            putInt("fontSize", userPreferences.fontSize)
            putBoolean("isDarkMode", userPreferences.isDarkMode)
            apply()
        }
    }

    private fun getPreferences(): UserPreferences {
        val sharedPreferences: SharedPreferences = getSharedPreferences(
            "MyPrefs",
            Context.MODE_PRIVATE
        )
        return UserPreferences(
            fontSize = sharedPreferences.getInt("fontSize", 16), // Default font size
            isDarkMode = sharedPreferences.getBoolean("isDarkMode", false) // Default theme
        )
    }
}


@Composable
fun PreferencesScreen(
    userPreferences: UserPreferences,
    onSavePreferences: (UserPreferences) -> Unit
) {
    var fontSizeState by remember { mutableFloatStateOf(userPreferences.fontSize.toFloat()) }
    var isDarkMode by remember { mutableStateOf(userPreferences.isDarkMode) }

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        item {
            Text(
                text = "Preferences",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        item {
            Slider(
                value = fontSizeState,
                onValueChange = { fontSizeState = it },
                valueRange = 12f..24f,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Font Size: ${fontSizeState.toInt()}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        item {
            Row(
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                    text = "Dark Mode",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )

                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { isDarkMode = it },
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        item {
            Button(
                onClick = { onSavePreferences(UserPreferences(fontSizeState.toInt(), isDarkMode)) },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("Save Preferences")
            }
        }
    }
}
@Preview
@Composable
fun PreferencesScreenPreview() {
    PreferencesScreen(
        userPreferences = UserPreferences(fontSize = 16, isDarkMode = true),
        onSavePreferences = {}
    )
}