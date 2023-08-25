package ci.orange.presentation.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import ci.orange.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import ci.orange.domain.repository.TuyaAuthRepository
import kotlinx.coroutines.launch

@HiltViewModel
class LoginRegisterViewModel @Inject constructor(private val repository:TuyaAuthRepository) :BaseViewModel(){

    var isLoginMode by mutableStateOf(true)
    private set

    var navigateToHomeScreen by mutableStateOf(false)
        private set

    var loaderVerificationCode by mutableStateOf(false)
        private set

    var login by mutableStateOf("")
    private set
    var password by mutableStateOf("")
        private set
    var validationCode by mutableStateOf(" ")
    private set

    var showValidationCode by mutableStateOf(false)
        private set

    fun onVerifyCodeEdit(value:String){
        validationCode = value.trim()
    }

    fun onLoginEdit(value:String){
        login = value
    }

    fun onPasswordEdit(value:String){
        password = value
    }

    fun sendVerificationCode(){
        val type = if(isLoginMode) 2 else 1
        viewModelScope.launch {
            loader = true
            val response = repository.sendVerificationCode(login, type = type)
            response.onSuccess {
                loader = false
                showValidationCode = true
            }
            response.onFailure {
                errorMessage = it.message
                loader = false
            }
        }
    }

    fun verificationCode(){
        viewModelScope.launch {
            loaderVerificationCode = true
            val response = repository.verifyCode(userName = login, code = validationCode, actionType = 1)
            response.onSuccess {
                loaderVerificationCode = false
                showValidationCode = false
                if(isLoginMode){
                    loginUser()
                }else{
                    registerUser()
                }
            }
            response.onFailure {
                errorMessage = it.message
                loader = false
            }
        }
    }

    private fun loginUser(){
        viewModelScope.launch {
            loader = true
            val response = repository.loginWithEmailAndPassword(email = login, password = password, verificationCode = validationCode)
            response.onSuccess {
                loader = false
                navigateToHomeScreen = true
            }
            response.onFailure {
                errorMessage = it.message
                loader = false
            }
        }
    }

    private fun registerUser(){
        viewModelScope.launch {
            loader = true
            val response = repository.registerWithEmailAndPassword(email = login, password = password, verificationCode = validationCode)
            response.onSuccess {
                loader = false
                navigateToHomeScreen = true
            }
            response.onFailure {
                errorMessage = it.message
                loader = false
            }
        }
    }


    fun onToggleLoginMode() {
        isLoginMode = !isLoginMode
    }


}