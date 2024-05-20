import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ContactsViewModel : ViewModel() {
    var name = mutableStateOf("")
        private set

    var email = mutableStateOf("")
        private set

    var message = mutableStateOf("")
        private set

    var isSubmitted = mutableStateOf(false)
        private set

    fun onNameChange(newName: String) {
        name.value = newName
    }

    fun onEmailChange(newEmail: String) {
        email.value = newEmail
    }

    fun onMessageChange(newMessage: String) {
        message.value = newMessage
    }

    fun onSubmit() {
        isSubmitted.value = true
        // Handle form submission, e.g., send data to a server
    }
}
