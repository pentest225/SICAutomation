package ci.orange.presentation.home

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.lifecycle.viewModelScope
import ci.orange.domain.repository.TuyaAuthRepository
import ci.orange.domain.repository.TuyaHomeRepository
import ci.orange.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.thingclips.smart.home.sdk.bean.HomeBean
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ci.orange.domain.repository.PressureUnit
import ci.orange.domain.repository.TempUnit
import ci.orange.domain.repository.WindSpeedUnit
import com.thingclips.smart.home.sdk.bean.DashBoardBean
import com.thingclips.smart.home.sdk.bean.WeatherBean


private const val TAG = "HomeViewModel"
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tuyaAuthRepository: TuyaAuthRepository,
    private val tuyaHomeRepository: TuyaHomeRepository
) : BaseViewModel() {

    val currentUser = tuyaAuthRepository.getConnectedUser()
    var homeList by mutableStateOf<MutableList<HomeBean>>(mutableListOf())

        private set
    var selectedHome by mutableStateOf<HomeBean?>(null)
    var weatherInfo by mutableStateOf<WeatherBean?>(null)
    var weatherDetail by mutableStateOf<MutableList<DashBoardBean>>(mutableListOf())
    init {
        Log.e("HomeViewModel", "Current User: ${currentUser.email} ${currentUser.username}")
        getHomeList()
    }

    private
    fun getHomeList() {
        viewModelScope.launch {
            val response = tuyaHomeRepository.getHomeList()
            response.onSuccess {
                if (it.isEmpty()) {
                    createHome()
                }else{
                    selectedHome = it.first()
                    Log.e(TAG, "getHomeList: SelectedHome $selectedHome", )
                    getWeather()
                    getWeatherList()
                }
                homeList.addAll(it)

            }
            response.onFailure {
                errorMessage = it.localizedMessage
            }
        }
    }

    private fun createHome() {
        viewModelScope.launch {
            loader = true
            val response = tuyaHomeRepository.createHome(
                "SICAutomationHome", 5.3398152177245075, -4.0043710423292245, "Cocody Danga",
                listOf("Bureau")
            )
            response.onSuccess {
                loader = false
                homeList.add(it)
                Log.e("HomeViewModel", "createHome: ${homeList.first()}")
            }
        }
    }

    private fun getWeather(){
        selectedHome?.let {
            viewModelScope.launch {
                val response = tuyaHomeRepository.getHomeWeatherSketch(it.homeId,it.lat,it.lon)
                response.onFailure {
                    errorMessage = it.localizedMessage
                    Log.e(TAG, "getWeather: $errorMessage", )
                }
                response.onSuccess {
                    weatherInfo  = it
                    Log.e(TAG, "getWeather: $errorMessage", )
                }
            }
        }
    }

    private fun getWeatherList(){
        selectedHome?.let {
            viewModelScope.launch {
                val response = tuyaHomeRepository.getHomeWeatherDetail(it.homeId,TempUnit.Celsius,PressureUnit.HPa,WindSpeedUnit.Ms)
                response.onFailure {
                    errorMessage = it.localizedMessage
                    Log.e(TAG, "getWeather: $errorMessage", )
                }
                response.onSuccess {
                    weatherDetail.addAll(it)
                    weatherDetail.forEach {
                        Log.e(TAG, "getWeather: ${it.value} ${it.unit}", )
                    }
                }
            }
        }
    }
}

