import com.c3dev.bagueton.ui.model.Image
import com.c3dev.bagueton.ui.model.Ingredient
import com.c3dev.bagueton.ui.model.RecipeBean
import com.c3dev.bagueton.ui.model.Step

fun fakeRecipes(): List<RecipeBean> {
    return listOf(
        RecipeBean(
            id = "coucou34",
            title = "Tourte aux Seigle",
            images = listOf(
                Image(id = null, "http://localhost:8082/images/tourte_aux_seigle.webp")
            ),
            ingredients = listOf(
                Ingredient(id = null,"Farine de seigle", "500"),
                Ingredient(id = null,"Eau", "350"),
                Ingredient(id = null,"Levure de boulanger", "15"),
                Ingredient(id = null,"Sel", "10"),
                Ingredient(id = null,"Miel", "30")
            ),
            steps = listOf(
                Step("Mélanger la farine de seigle et le sel dans un grand bol."),
                Step("Dissoudre la levure et le miel dans l'eau tiède, puis ajouter au mélange de farine."),
                Step("Pétrir la pâte jusqu'à ce qu'elle soit lisse et élastique, environ 10 minutes."),
                Step("Laisser lever la pâte pendant 1 à 2 heures jusqu'à ce qu'elle double de volume."),
                Step("Façonner la pâte en une boule et laisser lever encore 1 heure."),
                Step("Préchauffer le four à 220°C (425°F)."),
                Step("Faire des incisions sur le dessus de la tourte et cuire pendant 40 à 45 minutes jusqu'à ce qu'elle soit dorée.")
            )
        ),
    )
}



fun previewBaguetonViewModel(): com.c3dev.bagueton.ui.BaguetonViewModel {
    return com.c3dev.bagueton.ui.BaguetonViewModel().apply {
        recipeList.addAll(fakeRecipes())
        imageRecipe.value = recipeList.first().images?.first()?.url.toString()
        titleRecipe.value = recipeList.first().title


    }
}
