package com.c3dev.bagueton.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c3dev.bagueton.ui.model.Ingredient
import com.c3dev.bagueton.ui.model.RecipeAPI
import com.c3dev.bagueton.ui.model.RecipeBean
import com.c3dev.bagueton.ui.model.Step
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class BaguetonViewModel : ViewModel() {

    var recipeList = mutableStateListOf<RecipeBean>()
    var errorMessage = mutableStateOf("")

    var searchText = mutableStateOf("")

    var imageRecipe = mutableStateOf("")
    var titleRecipe = mutableStateOf("")
    var ingredientsList = mutableStateListOf<Ingredient>()
    var stepsList = mutableStateListOf<Step>()

    var newTitleRecipe = mutableStateOf("")
    var newStepsRecipe = mutableStateListOf<Step>()
    var newIngredientsRecipe = mutableStateListOf<Ingredient>()

    var editMode = mutableStateOf(false)

    fun createRecipe(title: String, ingredients: List<Ingredient>, steps: List<Step>){
        viewModelScope.launch(Dispatchers.Default) {
            ingredientsList.clear()
            stepsList.clear()
            try {
                if (RecipeAPI.isTitleUsed(title)) {
                    println("Le nom de recette $title est déjà utilisé.")
                    titleRecipe.value = "$title est déjà utilisé, choisissez un autre nom :)"
                    return@launch
                }
                println(ingredientsList)
                println(stepsList)
                ingredientsList.addAll(ingredients)
                stepsList.addAll(steps)
                RecipeAPI.createRecipe(title = title, steps = stepsList, ingredients = ingredientsList)
                val newRecipe = RecipeBean(title = title, steps = stepsList, ingredients = ingredientsList)
                launch(Dispatchers.Main) {
                    recipeList.add(newRecipe)
                }
            } catch (e: IOException) {
                e.printStackTrace()
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

    fun initializeNewIngredientsRecipe(ingredients: List<Ingredient>) {
        newIngredientsRecipe.clear()
        newIngredientsRecipe.addAll(ingredients)
    }

    fun initializeNewStepsRecipe(steps: List<Step>) {
        newStepsRecipe.clear()
        newStepsRecipe.addAll(steps)
    }

    fun updateIngredient(index: Int, newIngredient: String, newQuantity: String) {
        if (index < newIngredientsRecipe.size) {
            newIngredientsRecipe[index] = Ingredient(ingredient = newIngredient, quantity = newQuantity)
        }
    }

    fun updateStep(index: Int, newDescription: String) {
        if (index < newStepsRecipe.size) {
            newStepsRecipe[index] = Step(description = newDescription)
        }
    }
}
