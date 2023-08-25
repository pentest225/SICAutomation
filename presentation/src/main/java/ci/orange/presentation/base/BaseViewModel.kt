package ci.orange.presentation.base

import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel(){

    open var loader: Boolean by mutableStateOf(false)
        protected set

    open var loaderDataErrorMessage: String? by mutableStateOf(null)
        protected set
    open var errorMessage by mutableStateOf<String?>(null)
        protected set
    open var showToastOrSnackBar by mutableStateOf(false)
        protected set
    open var toastOrSnackBarMessage by mutableStateOf("")
        protected set
    open var showInfoPopup by mutableStateOf(false)
        protected set

    open var navigateToNextStep by mutableStateOf(false)
        protected set

    fun onNavigateNextStep(){
        navigateToNextStep = false
    }

    fun onShowToast(){
        showToastOrSnackBar = false
    }
    fun onShowInfoBar(){
        showInfoPopup = false
    }
    fun onShowError(){
        errorMessage = null
    }

    fun clearData(){
        loader = false
        errorMessage = null
        showInfoPopup = false
        showToastOrSnackBar = false
    }


}