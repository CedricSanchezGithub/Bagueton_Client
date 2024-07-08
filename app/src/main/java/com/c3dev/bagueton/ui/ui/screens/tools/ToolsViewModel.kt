package com.c3dev.bagueton.ui.ui.screens.tools

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.c3dev.bagueton.ui.model.beans.RecipeBean

class ToolsViewModel : ViewModel() {
    var multiplication = mutableStateOf("")
    var totalWeightRequest = mutableStateOf("")
    var selectedRecipe = mutableStateOf<RecipeBean?>(null)
    var expanded = mutableStateOf(false)
    var selectedOptionText = mutableStateOf("")
    private var newQuantityReturn = mutableStateOf("")

    val totalWeight: Int
        get() = selectedRecipe.value?.ingredients?.sumOf { it.quantity?.toIntOrNull() ?: 0 } ?: 0

    val multiplier: Int
        get() = multiplication.value.toIntOrNull() ?: 1

    fun newQuantity(quantity: String): String {
        val baseQuantity = quantity.toIntOrNull() ?: 0
        return when {
            totalWeightRequest.value.isNotEmpty() -> {
                val requestedWeight = totalWeightRequest.value.toIntOrNull() ?: 0
                if (totalWeight > 0) {
                    (baseQuantity * requestedWeight / totalWeight).toString()
                } else {
                    "0"
                }
            }
            multiplication.value.isNotEmpty() -> {
                (baseQuantity * multiplier).toString()
            }
            else -> quantity
        }
    }

    fun updateNewQuantity() {
        selectedRecipe.value?.ingredients?.forEach { ingredient ->
            ingredient.quantity?.let {
                newQuantityReturn.value = newQuantity(it)
            }
        }
    }
}
