package com.c3dev.bagueton.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.c3dev.bagueton.ui.model.RecipeBean

class ToolsViewModel : ViewModel() {
    var multiplication = mutableStateOf("")
    var isTotalWeightActive = mutableStateOf(false)
    var totalWeightRequest = mutableStateOf("")
    var selectedRecipe = mutableStateOf<RecipeBean?>(null)
    var expanded = mutableStateOf(false)
    var selectedOptionText = mutableStateOf("")
    val totalWeight: Int
        get() = selectedRecipe.value?.ingredients?.sumOf { it.quantity?.toIntOrNull() ?: 0 } ?: 0

    val multiplier: Int
        get() = multiplication.value.toIntOrNull() ?: 1

    fun newQuantity(weight: Int, totalWeight: Int, totalWeightRequest: Int, isTotalWeightActive: Boolean): String {
        val newWeight = weight * multiplier
        print("isTotalWeightActive: $isTotalWeightActive")
        if(isTotalWeightActive){
            print("${(weight / totalWeight * totalWeightRequest)}")
            return (weight / totalWeight * totalWeightRequest).toString()
        }
        return newWeight.toString()
    }
}
