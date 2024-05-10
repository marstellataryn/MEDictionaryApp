
package com.example.medictionaryapp.dataSource

data class Medication(
    val name: String,
    val disease: String
)

val medications = listOf(
    Medication("Metformin", "Diabetes"),
    Medication("Insulin", "Diabetes"),
    Medication("Ozempic", "Diabetes"),
    Medication("Statins", "Cholesterol"),
    Medication("Fenofibrate", "Cholesterol"),
    Medication("Lisinopril", "Hypertension"),
    Medication("Losartan", "Hypertension"),
    Medication("Valsartan", "Hypertension"),
    Medication("Amlodipine", "Hypertension"),
    Medication("Metoprolol", "Hypertension"),
    Medication("Hydrochlorothiazide", "Hypertension"),
    Medication("Heparin", "Blood thinner"),
    Medication("Aspirin", "Blood thinner")
)
