import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.c3dev.bagueton.ui.ui.ConnectivityViewModel
import com.c3dev.bagueton.ui.ui.MyBottomAppBar
import com.c3dev.bagueton.ui.ui.SearchBar
import com.c3dev.bagueton.ui.ui.screens.recipes.BaguetonViewModel

@Composable
fun TestScreen(baguetonViewModel: BaguetonViewModel,
               navHostController: NavHostController? = null,
               connectivityViewModel: ConnectivityViewModel? = null) {

    Scaffold(
        topBar = {
            // Passer l'instance de BaguetonViewModel à SearchBar
            SearchBar(baguetonViewModel = baguetonViewModel)
        },
        bottomBar = {
            // Passer l'instance de NavHostController à MyBottomAppBar
            MyBottomAppBar(navHostController = navHostController)
        }


    ){innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)){

            Spacer(modifier = Modifier.height(16.dp))
            if (connectivityViewModel != null) {
                ConnectivityStatusScreen(connectivityViewModel)
            }

        }
    }
}

@Composable
fun ConnectivityStatusScreen(connectivityViewModel: ConnectivityViewModel) {
    val isConnected = connectivityViewModel.networkStatus.observeAsState(false)
    if (isConnected.value) {
        Text("Connected")
    } else {
        Text("Not Connected")
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Surface {
            TestScreen( baguetonViewModel = BaguetonViewModel())
        }
    }
}
