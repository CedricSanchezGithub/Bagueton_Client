package com.c3dev.bagueton.ui.ui.screens.recipes

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.c3dev.bagueton.R
import com.c3dev.bagueton.ui.model.beans.Ingredient
import com.c3dev.bagueton.ui.model.beans.RecipeBean
import com.c3dev.bagueton.ui.model.beans.Step
import com.c3dev.bagueton.ui.ui.ConfirmDeleteDialog
import com.c3dev.bagueton.ui.ui.MyBottomAppBar
import com.c3dev.bagueton.ui.ui.SearchBar
import com.c3dev.bagueton.ui.ui.theme.Bagueton_v1Theme

@Composable
fun RecipeScreen(
    id: String?,
    navHostController: NavHostController? = null,
    baguetonViewModel: BaguetonViewModel
) {
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            SearchBar(
                baguetonViewModel = baguetonViewModel,
                navHostController = navHostController
            )
        },
        bottomBar = {
            MyBottomAppBar(navHostController = navHostController)
        },
        floatingActionButton = {
            if (!baguetonViewModel.editMode.value){
                val recipe = baguetonViewModel.recipeList.find { it.id == id }
                FloatingActionButton(
                    containerColor = MaterialTheme.colorScheme.primary,
                    onClick = {
                        recipe?.let {
                            baguetonViewModel.newStepsRecipe.clear()
                            baguetonViewModel.newStepsRecipe.addAll(baguetonViewModel.recipeList.find { it.id == id }?.steps ?: emptyList())

                            baguetonViewModel.newIngredientsRecipe.clear()
                            baguetonViewModel.newIngredientsRecipe.addAll(baguetonViewModel.recipeList.find { it.id == id }?.ingredients ?: emptyList())

                        }
                        baguetonViewModel.editMode.value = true
                    }
                ) {
                    Icon(
                        Icons.Filled.Edit, stringResource(id = R.string.update_recipe),
                        Modifier.background(MaterialTheme.colorScheme.primary)
                    )
                }
            } else {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)) {


                    FloatingActionButton(
                        containerColor = MaterialTheme.colorScheme.primary,
                        onClick = {
                            baguetonViewModel.editMode.value = false
                        }
                    ) {
                        Icon(Icons.Filled.Clear, stringResource(id = R.string.cancel), Modifier.background(MaterialTheme.colorScheme.primary))
                    }

                    FloatingActionButton(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        onClick = {
                            baguetonViewModel.saveRecipe(id)
                        }
                    ) {
                        Icon(Icons.Filled.Save, stringResource(id = R.string.save), Modifier.background(MaterialTheme.colorScheme.tertiary))
                    }

                    FloatingActionButton(
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        containerColor = MaterialTheme.colorScheme.error,
                        onClick = {
                            showDialog = true
                        }
                    ) {
                        Icon(Icons.Filled.Delete, stringResource(id = R.string.delete), Modifier.background(MaterialTheme.colorScheme.error))
                    }
                }
            }
        }
    )  { innerPadding ->
        val recipe = baguetonViewModel.recipeList.find { it.id == id }
        val editMode = baguetonViewModel.editMode.value
        val scrollState = rememberScrollState()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(innerPadding)
                .fillMaxHeight()
                .verticalScroll(scrollState)
        ) {
            if(!editMode){
                if (recipe != null) {
                    HeaderRecipeScreen(recipe)
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (recipe != null) {
                    BodyRecipeScreen(recipe)
                }
            } else {
                if (recipe != null) {
                    UpdateHeaderRecipeScreen(baguetonViewModel, recipe)
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (recipe != null) {
                    UpdateBodyRecipeScreen(baguetonViewModel, recipe)
                }
            }
        }
        if (showDialog) {
            ConfirmDeleteDialog(
                onConfirm = {
                    baguetonViewModel.deleteRecipe(id)
                    showDialog = false
                    baguetonViewModel.editMode.value = false
                    baguetonViewModel.loadRecipes()
                    navHostController?.navigate("HomeScreen")
                },
                onDismiss = { showDialog = false }
            )
        }
    }
}


@Composable
fun HeaderRecipeScreen(recipe: RecipeBean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp

        Box {
            recipe.let {
                Image(
                    painter = rememberAsyncImagePainter(it.images?.firstOrNull()?.url),
                    contentDescription = it.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = screenHeight / 3)
                        .padding(8.dp)
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(50.dp, 100.dp, 50.dp, 100.dp)
                        )
                        .clip(RoundedCornerShape(50.dp, 100.dp, 50.dp, 100.dp)),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = it.title,
                    fontSize = 30.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(32.dp)
                        .fillMaxWidth(),
                    color = Color.White,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(4f, 4f),
                            blurRadius = 8f
                        )
                    )
                )
            }
        }
    }
}

@Composable
fun UpdateHeaderRecipeScreen(baguetonViewModel: BaguetonViewModel, recipe: RecipeBean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp

        Box(contentAlignment = Alignment.Center) {
            recipe.let {
                Image(
                    painter = rememberAsyncImagePainter(it.images?.firstOrNull()?.url),
                    contentDescription = it.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = screenHeight / 3)
                        .padding(8.dp)
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(50.dp, 100.dp, 50.dp, 100.dp)
                        )
                        .clip(RoundedCornerShape(50.dp, 100.dp, 50.dp, 100.dp)),
                    contentScale = ContentScale.Crop
                )
                UpdateTitleInput(
                    title = baguetonViewModel.newTitleRecipe.value,
                    onTitleChange = { newTitle ->
                        baguetonViewModel.newTitleRecipe.value = newTitle
                    },
                )
            }
        }
    }
}
@Composable
fun UpdateTitleInput(
    title: String,
    onTitleChange: (String) -> Unit,
) {
    var isTitleFocused by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
                .border(1.dp, Color.Gray)
                .width(300.dp)
                .background(Color.White)
                .onFocusChanged { focusState ->
                    isTitleFocused = focusState.isFocused
                }
        ) {
            if (title.isEmpty() && !isTitleFocused) {
                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.recipe_title_new),
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            BasicTextField(
                value = title,
                onValueChange = onTitleChange,
                singleLine = true,
                modifier = Modifier
                    .width(300.dp)
                    .padding(8.dp)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(50.dp, 100.dp, 50.dp, 100.dp))
            )
        }
    }
}
@Composable
fun BodyRecipeScreen(recipe: RecipeBean) {
    Column(Modifier.padding(32.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp, max = 300.dp) // Donne une hauteur finie à la Box
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp))
                .align(Alignment.CenterHorizontally)
                .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp))
                .padding(32.dp)
        ) {
            recipe.ingredients?.let { IngredientList(ingredients = it) }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp, max = 300.dp) // Donne une hauteur finie à la Box
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp))
                .align(Alignment.CenterHorizontally)
                .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp))
                .padding(32.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                        Text(text = stringResource(id = R.string.step_list), modifier = Modifier.padding(16.dp), fontSize = 20.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, textDecoration = TextDecoration.Underline)
                }
                itemsIndexed(recipe.steps ?: emptyList()) { index, step ->
                    Text(
                        text = "${index + 1}. ${step.description}",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun UpdateBodyRecipeScreen(baguetonViewModel: BaguetonViewModel, recipe: RecipeBean) {
    Column(Modifier.padding(32.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp, max = 300.dp)
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp))
                .align(Alignment.CenterHorizontally)
                .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp))
                .padding(32.dp)
        ) {
            recipe.ingredients?.let { UpdateIngredientList(baguetonViewModel, it) }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp, max = 300.dp)
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp))
                .align(Alignment.CenterHorizontally)
                .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp))
                .padding(32.dp)
        ) {
            recipe.steps?.let { UpdateStepList(baguetonViewModel, it) }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun IngredientList(ingredients: List<Ingredient>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = stringResource(id = R.string.ingredient_list), modifier = Modifier.padding(16.dp), fontSize = 20.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, textDecoration = TextDecoration.Underline)
        }
        items(ingredients) { ingredient ->
            Text(
                text = "${ingredient.ingredient}, ${ingredient.quantity}",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
        }
    }
}

@Composable
fun UpdateIngredientList(baguetonViewModel: BaguetonViewModel, ingredients: List<Ingredient>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = stringResource(id = R.string.ingredient_list_new), modifier = Modifier.padding(16.dp), fontSize = 20.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, textDecoration = TextDecoration.Underline)
        }
        itemsIndexed(ingredients) { index, ingredient ->
            UpdateIngredientInput(
                baguetonViewModel = baguetonViewModel,
                index = index, // passer l'index ici
                onIngredientChange = { newIngredient ->
                    val updatedIngredient = ingredient.copy(ingredient = newIngredient)
                    updatedIngredient.ingredient?.let {
                        updatedIngredient.quantity?.let { it1 ->
                            baguetonViewModel.updateIngredient(index,
                                it, it1
                            )
                        }
                    }
                },
                onQuantityChange = { newQuantity ->
                    val updatedQuantity = newQuantity.filter { it.isDigit() }
                    val updatedIngredient = ingredient.copy(quantity = updatedQuantity)
                    updatedIngredient.ingredient?.let {
                        updatedIngredient.quantity?.let { it1 ->
                            baguetonViewModel.updateIngredient(index,
                                it, it1
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun UpdateIngredientInput(
    baguetonViewModel: BaguetonViewModel,
    index: Int,
    onIngredientChange: (String) -> Unit,
    onQuantityChange: (String) -> Unit
) {
    var isIngredientFocused by remember { mutableStateOf(false) }
    var isQuantityFocused by remember { mutableStateOf(false) }

    val currentIngredient = if (index < baguetonViewModel.newIngredientsRecipe.size) {
        baguetonViewModel.newIngredientsRecipe[index]
    } else {
        Ingredient("", "", "")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray)
                .padding(8.dp)
                .onFocusChanged { focusState ->
                    isIngredientFocused = focusState.isFocused
                }
        ) {
            if (currentIngredient.ingredient!!.isEmpty() && !isIngredientFocused) {
                Text(
                    text = stringResource(id = R.string.ingredient),
                    color = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
            }
            BasicTextField(
                value = currentIngredient.ingredient ?: "",
                onValueChange = {
                    baguetonViewModel.updateIngredient(index, it, currentIngredient.quantity ?: "")
                    onIngredientChange(it)
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray)
                .padding(8.dp)
                .onFocusChanged { focusState ->
                    isQuantityFocused = focusState.isFocused
                }
        ) {
            if (currentIngredient.quantity!!.isEmpty() && !isQuantityFocused) {
                Text(
                    text = stringResource(id = R.string.quantity),
                    color = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
            }
            BasicTextField(
                value = currentIngredient.quantity ?: "",
                onValueChange = { it ->
                    val filteredQuantity = it.filter { it.isDigit()
                    }
                    baguetonViewModel.updateIngredient(index, currentIngredient.ingredient ?: "", filteredQuantity)
                    onQuantityChange(filteredQuantity)
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun UpdateStepList(baguetonViewModel: BaguetonViewModel, steps: List<Step>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = stringResource(id = R.string.step_list_new),
                modifier = Modifier.padding(16.dp),
                fontSize = 20.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        }
        itemsIndexed(steps) { index, step ->
            if (index < baguetonViewModel.newStepsRecipe.size) {
                UpdateStepInput(
                    baguetonViewModel = baguetonViewModel,
                    index = index,
                    step = step,
                    onStepChange = { newDescription ->
                        baguetonViewModel.updateStep(index, newDescription)
                    }
                )
            }
        }
    }
}

@Composable
fun UpdateStepInput(
    baguetonViewModel: BaguetonViewModel,
    index: Int,
    step: Step,
    onStepChange: (String) -> Unit
) {
    var isStepFocused by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray)
                .padding(8.dp)
                .onFocusChanged { focusState ->
                    isStepFocused = focusState.isFocused
                }
        ) {
            if (step.description!!.isEmpty() && !isStepFocused) {
                Text(
                    text = stringResource(id = R.string.step),
                    color = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
            }

            if (index < baguetonViewModel.newStepsRecipe.size) {
                BasicTextField(
                    value = baguetonViewModel.newStepsRecipe[index].description ?: "",
                    onValueChange = {
                        baguetonViewModel.newStepsRecipe[index] = baguetonViewModel.newStepsRecipe[index].copy(description = it)
                        onStepChange(it)
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                // Placeholder in case the index is out of bounds
                Text(
                    text = step.description ?: "",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}




@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecipeScreenPreview() {
    Bagueton_v1Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            RecipeScreen( baguetonViewModel = BaguetonViewModel(), id = "1", navHostController = null)
        }
    }
}
