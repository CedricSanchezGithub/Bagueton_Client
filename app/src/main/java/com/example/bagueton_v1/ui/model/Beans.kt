package com.example.bagueton_v1.ui.model

import com.example.bagueton_v1.R

data class Ingredient(val name: String, val quantity: String)

data class Recipe(
    val id: Int,
    val name: String,
    val steps: String,
    val imageResId: Int,
    val ingredients: List<Ingredient>
)

    // Création des ingrédients pour la recette de croissant
    val ingredients = listOf(
        Ingredient("Farine", "500g"),
        Ingredient("Eau", "140ml"),
        Ingredient("Lait", "60ml"),
        Ingredient("Sucre", "50g"),
        Ingredient("Beurre doux", "300g pour le tourage"),
        Ingredient("Levure fraîche", "15g"),
        Ingredient("Sel", "10g"),
        Ingredient("Beurre pour la pâte", "50g")
    )

data class WeatherBean(
    var current: Current,
    var location: Location
)

data class Current(
    var cloud: Int,
    var condition: Condition,
    var feelslike_c: Double,
    var feelslike_f: Double,
    var gust_kph: Double,
    var gust_mph: Int,
    var humidity: Int,
    var is_day: Int,
    var last_updated: String,
    var last_updated_epoch: Int,
    var precip_in: Double,
    var precip_mm: Double,
    var pressure_in: Double,
    var pressure_mb: Int,
    var temp_c: Int,
    var temp_f: Int,
    var uv: Int,
    var vis_km: Int,
    var vis_miles: Int,
    var wind_degree: Int,
    var wind_dir: String,
    var wind_kph: Double,
    var wind_mph: Double
)

data class Location(
    var country: String,
    var lat: Double,
    var localtime: String,
    var localtime_epoch: Int,
    var lon: Double,
    var name: String,
    var region: String,
    var tz_id: String
)

data class Condition(
    var code: Int,
    var icon: String,
    var text: String
)