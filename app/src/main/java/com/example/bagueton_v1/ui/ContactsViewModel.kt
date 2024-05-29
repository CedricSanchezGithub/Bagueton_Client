import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bagueton_v1.ui.model.ContactsFormAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class ContactsViewModel : ViewModel() {
    var name = mutableStateOf("")
    var email = mutableStateOf("")
    var message = mutableStateOf("")
    var isSubmitted = mutableStateOf(false)


    fun createForm(name: String, email: String, message: String){
        viewModelScope.launch(Dispatchers.Default) {

            try {
                ContactsFormAPI.createContactForm(email, name, message)
                isSubmitted.value = true
            }
            catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}
