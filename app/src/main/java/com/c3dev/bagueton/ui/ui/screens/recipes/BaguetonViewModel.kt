package com.c3dev.bagueton.ui.ui.screens.recipes

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c3dev.bagueton.ui.model.beans.Ingredient
import com.c3dev.bagueton.ui.model.beans.RecipeBean
import com.c3dev.bagueton.ui.model.beans.Step
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class BaguetonViewModel : ViewModel() {

    var recipeList = mutableStateListOf<RecipeBean>()
    var errorMessage = mutableStateOf("")

    var searchText = mutableStateOf("")

    var titleRecipe = mutableStateOf("")
    var ingredientsList = mutableStateListOf<Ingredient>()
    var stepsList = mutableStateListOf<Step>()

    var newTitleRecipe = mutableStateOf("")
    var newStepsRecipe = mutableStateListOf<Step>()
    var newIngredientsRecipe = mutableStateListOf<Ingredient>()

    var editMode = mutableStateOf(false)
    var httpCode = mutableIntStateOf(0)

    fun clearRecipe(){

        titleRecipe.value = ""
        ingredientsList.clear()
        stepsList.clear()

    }

    fun createRecipe(title: String, ingredients: List<Ingredient>, steps: List<Step>) {
        viewModelScope.launch(Dispatchers.Default) {
            println("Données de stepsList avant clear : ${stepsList.map { it.description }}")

            try {
                if (RecipeAPI.isTitleUsed(title)) {
                    println("Le nom de recette $title est déjà utilisé.")
                    withContext(Dispatchers.Main) {
                        titleRecipe.value = "$title est déjà utilisé, choisissez un autre nom :)"
                    }
                    return@launch  // Sortie anticipée de la coroutine si le titre est déjà utilisé
                }

                ingredientsList.addAll(ingredients)
                println("Données de steps avant addAll : ${steps.map { it.description }}")
                stepsList.addAll(steps)
                println("Données de stepsList après addAll : ${stepsList.map { it.description }}")

                val response = RecipeAPI.createRecipe(title = title, steps = stepsList, ingredients = ingredientsList)
                withContext(Dispatchers.Main) {
                    httpCode.intValue = response.code
                    if (response.code == 200) {
                        val newRecipe = RecipeBean(title = title, steps = stepsList, ingredients = ingredientsList)
                        recipeList.add(newRecipe)
                    } else {
                        println("Erreur lors de la création de la recette : ${response.body}")
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    println("Erreur réseau : ${e.message}")
                }
            }
        }
    }

    fun loadRecipes() {
        viewModelScope.launch(Dispatchers.Default) {
            recipeList.clear()
            try {
                val newRecipes = RecipeAPI.readRecipes()
                launch(Dispatchers.Main) {
                    recipeList.addAll(newRecipes)
                    errorMessage.value = ""
                    println("Chargement des données de recette loadRecipe dans le ViewModel")                }
            } catch (e: Throwable) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
                launch(Dispatchers.Main) {
                    errorMessage.value =
                        "Erreur de connexion Internet. Veuillez vérifier votre connexion."
                }
            } catch (e: Exception) {
                e.printStackTrace()
                launch(Dispatchers.Main) {
                    errorMessage.value =
                        "Erreur lors du chargement des recettes. Veuillez réessayer plus tard."
                }
            }
        }
    }


    fun saveRecipe(id: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (id != null) {
                val updatedRecipe = RecipeBean(
                    id = id,
                    title = newTitleRecipe.value,
                    ingredients = newIngredientsRecipe,
                    steps = newStepsRecipe
                )
                try {
                    RecipeAPI.updateRecipe(id, updatedRecipe.title, updatedRecipe.ingredients, updatedRecipe.steps)
                    println("recette correctement envoyé au serveur $updatedRecipe")
                    withContext(Dispatchers.Main) {
                        val index = recipeList.indexOfFirst { it.id == id }
                        if (index != -1) {
                            recipeList[index] = updatedRecipe
                        }
                        editMode.value = false
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun deleteRecipe(idRecipe: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {

            RecipeAPI.deleteRecipe(idRecipe)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    fun addStep() {
        stepsList.add(Step(description = ""))
    }

    fun addIngredient() {
        ingredientsList.add(Ingredient(ingredient = "", quantity = ""))
    }

    fun updateIngredient(index: Int, newIngredient: String, newQuantity: String) {
        if (index < ingredientsList.size) {
            ingredientsList[index] = Ingredient(ingredient = newIngredient, quantity = newQuantity)
        } else {
            println("Invalid index $index for updating ingredient")
        }
    }

    fun updateStep(index: Int, newDescription: String) {
        if (index < stepsList.size) {
            stepsList[index] = Step(description = newDescription)
        } else {
            println("Invalid index $index for updating step")
        }
    }
}
