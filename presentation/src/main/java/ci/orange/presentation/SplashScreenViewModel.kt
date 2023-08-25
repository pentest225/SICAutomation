package ci.orange.presentation

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.viewModelScope
import ci.orange.domain.repository.TuyaAuthRepository
import ci.orange.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf

@HiltViewModel
class SplashScreenViewModel @Inject constructor(private val repository:TuyaAuthRepository) :BaseViewModel(){
     var navigateToLoginScreen by mutableStateOf(false)
         private set
     var navigateToHomeScreen by mutableStateOf(false)
         private set

    init {
        runSplashFunction()
    }

    fun onNavigate(){
        navigateToHomeScreen = false
        navigateToLoginScreen = false
    }

    private fun runSplashFunction(){
       viewModelScope.launch {
           val response = repository.userIsAuth()
           response.onSuccess {
               if(it){
                   navigateToHomeScreen = true
               }else {
                   navigateToLoginScreen = true
               }
           }
           response.onFailure {
               navigateToLoginScreen = true
           }
       }
    }
}