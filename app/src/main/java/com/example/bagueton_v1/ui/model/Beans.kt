package com.example.bagueton_v1.ui.model

//data class Ingredient(val name: String, val quantity: String)
//
//data class Recipe(
//    val id: Int,
//    val name: String,
//    val steps: String,
//    val imageResId: Int,
//    val ingredients: List<Ingredient>
//)



data class RecipeBean(
    var id: String? = null,
    var title: String,
    var images: List<Image>? = null,
    var ingredients: List<Ingredient>,
    var steps: List<Step>? = null
)

data class Image(
    var id: String? = null,
    var url: String
)

data class Ingredient(
    var id: String ? = null,
    var ingredient: String?,
    var quantity: String?
)

data class Step(
    var description: String?,
    var id: String ? = null
)


data class WeatherBean(
    var base: String,
    var clouds: Clouds,
    var cod: Int,
    var coord: Coord,
    var dt: Int,
    var id: Int,
    var main: Main,
    var name: String,
    var sys: Sys,
    var timezone: Int,
    var visibility: Int,
    var weather: List<Weather>,
    var wind: Wind
)

data class Clouds(
    var all: Int
)

data class Coord(
    var lat: Double,
    var lon: Double
)

data class Main(
    var feels_like: Double,
    var humidity: Int,
    var pressure: Int,
    var temp: Double,
    var temp_max: Double,
    var temp_min: Double
)

data class Sys(
    var country: String,
    var id: Int,
    var sunrise: Int,
    var sunset: Int,
    var type: Int
)

data class Weather(
    var description: String,
    var icon: String,
    var id: Int,
    var main: String
)

data class Wind(
    var deg: Int,
    var speed: Double
)

data class ContactFormBean(

    var  id: String?,
    var email: String,
    var message: String,
    var name: String

)

//data class WeatherBeans ( var weather: Weather )
//data class Weather(
//    var columns: Columns,
//    var locations: Locations,
//    var messages: Any,
//    var queryCost: Int,
//    var remainingCost: Int
//)
//
//data class Columns(
//    var address: Address,
//    var cape: Cape,
//    var cin: Cin,
//    var conditions: Conditions,
//    var datetime: Datetime,
//    var humidity: Humidity,
//    var latitude: Latitude,
//    var longitude: Longitude,
//    var maxt: Maxt,
//    var mint: Mint,
//    var name: Name,
//    var pop: Pop,
//    var precip: Precip,
//    var preciptype: Preciptype,
//    var severerisk: Severerisk,
//    var temp: Temp,
//    var wspd: Wspd
//)
//
//data class Locations(
// var boisseron: Boisseron)
//
//data class Address(
//    var id: String,
//    var name: String,
//    var type: Int,
//    var unit: Any
//)
//
//data class Cape(
//    var id: String,
//    var name: String,
//    var type: Int,
//    var unit: Any
//)
//
//data class Cin(
//    var id: String,
//    var name: String,
//    var type: Int,
//    var unit: Any
//)
//
//
//
//data class Conditions(
//    var id: String,
//    var name: String,
//    var type: Int,
//    var unit: Any
//)
//
//data class Datetime(
//    var id: String,
//    var name: String,
//    var type: Int,
//    var unit: Any
//)
//
//
//data class Humidity(
//    var id: String,
//    var name: String,
//    var type: Int,
//    var unit: Any
//)
//
//data class Latitude(
//    var id: String,
//    var name: String,
//    var type: Int,
//    var unit: Any
//)
//
//data class Longitude(
//    var id: String,
//    var name: String,
//    var type: Int,
//    var unit: Any
//)
//
//data class Maxt(
//    var id: String,
//    var name: String,
//    var type: Int,
//    var unit: String
//)
//
//data class Mint(
//    var id: String,
//    var name: String,
//    var type: Int,
//    var unit: String
//)
//
//data class Name(
//    var id: String,
//    var name: String,
//    var type: Int,
//    var unit: Any
//)
//
//data class Pop(
//    var id: String,
//    var name: String,
//    var type: Int,
//    var unit: Any
//)
//
//data class Precip(
//    var id: String,
//    var name: String,
//    var type: Int,
//    var unit: String
//)
//
//data class Preciptype(
//    var id: String,
//    var name: String,
//    var type: Int,
//    var unit: Any
//)
//
//data class Severerisk(
//    var id: String,
//    var name: String,
//    var type: Int,
//    var unit: Any
//)
//
//
//
//data class Temp(
//    var id: String,
//    var name: String,
//    var type: Int,
//    var unit: String
//)
//
//data class Wspd(
//    var id: String,
//    var name: String,
//    var type: Int,
//    var unit: String
//)
//
//data class Boisseron(
//    var address: String,
//    var alerts: Any,
//    var currentConditions: CurrentConditions,
//    var distance: Double,
//    var id: String,
//    var index: Int,
//    var latitude: Double,
//    var longitude: Double,
//    var name: String,
//    var stationContributions: Any,
//    var time: Double,
//    var tz: String,
//    var values: List<Value>
//)

data class CurrentConditions(
    var cloudcover: Double,
    var datetime: String,
    var dew: Double,
    var heatindex: Any,
    var humidity: Double,
    var icon: String,
    var moonphase: Double,
    var precip: Double,
    var sealevelpressure: Double,
    var snowdepth: Any,
    var stations: String,
    var sunrise: String,
    var sunset: String,
    var temp: Double,
    var visibility: Double,
    var wdir: Double,
    var wgust: Double,
    var windchill: Any,
    var wspd: Double
)

data class Value(
    var cape: Double,
    var cin: Double,
    var cloudcover: Double,
    var conditions: String,
    var datetime: Long,
    var datetimeStr: String,
    var dew: Double,
    var heatindex: Any,
    var humidity: Double,
    var maxt: Double,
    var mint: Double,
    var pop: Double,
    var precip: Double,
    var preciptype: String,
    var sealevelpressure: Double,
    var severerisk: Double,
    var snow: Double,
    var snowdepth: Double,
    var solarenergy: Double,
    var solarradiation: Double,
    var temp: Double,
    var uvindex: Double,
    var visibility: Double,
    var wdir: Double,
    var wgust: Double,
    var windchill: Any,
    var wspd: Double
)

