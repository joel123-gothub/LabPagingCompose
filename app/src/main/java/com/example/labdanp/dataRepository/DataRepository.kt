package com.example.labdanp.dataRepository

import android.content.Context
import com.example.labdanp.datos.Data
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataRepository(private val context: Context) {
    fun readDataFromJson(): List<Data> {
        val json = readJsonFileFromAssets("data.json")
        return parseDataFromJson(json)
    }

    private fun readJsonFileFromAssets(fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    private fun parseDataFromJson(json: String): List<Data> {
        val gson = Gson()
        return gson.fromJson(json, object : TypeToken<List<Data>>() {}.type)
    }
}