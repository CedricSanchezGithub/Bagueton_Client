package com.example.bagueton_v1.ui

import com.example.bagueton_v1.ui.model.RecipeBean

fun fakeRecipes(): List<RecipeBean> {
    return listOf(
        RecipeBean(
            idRecipe = 3000,
            title = "Quiche Lorraine",
            ingredients = "Lardons, œufs, crème fraîche, pâte brisée",
            steps = "Étaler la pâte, disperser les lardons, mélanger œufs et crème, verser, cuire.",
            image = "http://90.51.140.217:8081/campagne.jpg"
        ),
        RecipeBean(
            idRecipe = 4000,
            title = "Ratatouille",
            ingredients = "Aubergines, courgettes, poivrons, tomates, oignon",
            steps = "Couper les légumes, les faire revenir séparément, les mélanger, mijoter.",
            image = "http://90.51.140.217:8081/campagne.jpg"
        ),
        RecipeBean(
            idRecipe = 5000,
            title = "Velouté de champignons",
            ingredients = "Champignons, bouillon de volaille, crème, oignon",
            steps = "Suer les oignons, ajouter champignons, bouillon, cuire, mixer, ajouter crème.",
            image = "http://90.51.140.217:8081/campagne.jpg"
        ),
        RecipeBean(
            idRecipe = 6000,
            title = "Salade César",
            ingredients = "Laitue romaine, croûtons, parmesan, sauce César",
            steps = "Laver la laitue, mélanger avec croûtons et parmesan, ajouter sauce.",
            image = "http://90.51.140.217:8081/campagne.jpg"
        ),
        RecipeBean(
            idRecipe = 7000,
            title = "Soupe à l'oignon",
            ingredients = "Oignons, bouillon de bœuf, vin blanc, baguette, fromage gruyère",
            steps = "Caraméliser les oignons, déglacer au vin, ajouter bouillon, cuire, gratiner.",
            image = "http://90.51.140.217:8081/campagne.jpg"
        ),
        RecipeBean(
            idRecipe = 8000,
            title = "Lasagnes à la bolognaise",
            ingredients = "Feuilles de lasagne, sauce bolognaise, béchamel, fromage",
            steps = "Monter les lasagnes en alternant couches de sauce et pâtes, cuire au four.",
            image = "http://90.51.140.217:8081/campagne.jpg"
        ),
        RecipeBean(
            idRecipe = 9000,
            title = "Crêpes",
            ingredients = "Farine, œufs, lait, beurre, sel",
            steps = "Mélanger tous les ingrédients pour obtenir une pâte lisse, cuire.",
            image = "http://90.51.140.217:8081/campagne.jpg"
        ),
        RecipeBean(
            idRecipe = 10000,
            title = "Mousse au chocolat",
            ingredients = "Chocolat noir, œufs, sucre",
            steps = "Fondre le chocolat, incorporer jaunes d'œufs, monter blancs en neige, mélanger.",
            image = "http://90.51.140.217:8081/campagne.jpg"
        ),
        RecipeBean(
            idRecipe = 11000,
            title = "Poulet basquaise",
            ingredients = "Poulet, poivrons, tomates, oignon, piment d'Espelette",
            steps = "Saisir le poulet, ajouter légumes et épices, cuire à couvert.",
            image = "http://90.51.140.217:8081/campagne.jpg"
        ),
        RecipeBean(
            idRecipe = 12000,
            title = "Gâteau au yaourt",
            ingredients = "Yaourt, farine, sucre, huile, œufs",
            steps = "Mélanger ingrédients, verser dans un moule, cuire au four.",
            image = "http://90.51.140.217:8081/campagne.jpg"
        )
    )
}


fun previewBaguetonViewModel(): BaguetonViewModel {
    return BaguetonViewModel().apply {
        recipeList.addAll(fakeRecipes())
        searchText.value = "Chercher une recette..."
        titleRecipe.value = "Bienvenue dans l'app Bagueton!"
        stepsRecipe.value = "Voici les étapes pour préparer votre recette préférée."
        ingredientsRecipe.value = "Liste des ingrédients ici."
        snackBarValue.value = true
    }
}
