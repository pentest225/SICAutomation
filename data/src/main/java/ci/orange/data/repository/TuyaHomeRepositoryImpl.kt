package ci.orange.data.repository

import ci.orange.domain.model.MyException
import ci.orange.domain.repository.PressureUnit
import ci.orange.domain.repository.TempUnit
import ci.orange.domain.repository.TuyaHomeRepository
import ci.orange.domain.repository.WindSpeedUnit
import com.thingclips.smart.home.sdk.ThingHomeSdk
import com.thingclips.smart.home.sdk.bean.DashBoardBean
import com.thingclips.smart.home.sdk.bean.HomeBean
import com.thingclips.smart.home.sdk.bean.WeatherBean
import com.thingclips.smart.home.sdk.callback.IGetHomeWetherCallBack
import com.thingclips.smart.home.sdk.callback.IIGetHomeWetherSketchCallBack
import com.thingclips.smart.home.sdk.callback.IThingGetHomeListCallback
import com.thingclips.smart.home.sdk.callback.IThingHomeResultCallback
import com.thingclips.smart.sdk.api.IResultCallback
import kotlinx.coroutines.suspendCancellableCoroutine


class TuyaHomeRepositoryImpl:TuyaHomeRepository {
    override suspend fun createHome(
        name: String,
        lat: Double,
        long: Double,
        locationPlace: String,
        rooms: List<String>
    ): Result<HomeBean> {
        return try {
            suspendCancellableCoroutine {continuation ->
                ThingHomeSdk.getHomeManagerInstance().createHome(name,long,lat,locationPlace,rooms,object :IThingHomeResultCallback {
                    override fun onSuccess(bean: HomeBean?) {
                        bean?.let {
                            continuation.resume(Result.success(it), onCancellation = null)
                        }?: continuation.resume(Result.failure(MyException("Error to create home")) ,null)
                    }

                    override fun onError(errorCode: String?, errorMsg: String?) {
                        continuation.resume(Result.failure(MyException("Login onError: $errorCode - $errorMsg")) ,null)
                    }

                })
            }
        }catch (e:Exception){
            Result.failure(e)
        }
    }

    override suspend fun updateHomeInfo(
        homeId:Long,
        name: String,
        lat: Double,
        long: Double,
        locationPlace: String,
        rooms: List<String>
    ): Result<Boolean> {
       return try {
            suspendCancellableCoroutine<Result<Boolean>> { continuation ->
                ThingHomeSdk.newHomeInstance(homeId).updateHome(name,long,lat,locationPlace,object :IResultCallback{
                    override fun onError(code: String?, error: String?) {
                        continuation.resume(Result.failure(MyException("Login onError: $code - $error")) ,null)
                    }

                    override fun onSuccess() {
                        continuation.resume(Result.success(true), onCancellation = null)
                    }

                })
            }
        }catch (e:Exception){
            Result.failure(e)
        }
    }

    override suspend fun getHomeList(): Result<List<HomeBean>> {
       return try {
            suspendCancellableCoroutine { continuation ->
                ThingHomeSdk.getHomeManagerInstance().queryHomeList(object :
                    IThingGetHomeListCallback{
                    override fun onSuccess(homeBeans: MutableList<HomeBean>?) {
                        homeBeans?.let {
                            continuation.resume(Result.success(it), onCancellation = null)
                        }?:continuation.resume(Result.failure(MyException("Error to get Home List")) ,null)
                    }

                    override fun onError(errorCode: String?, error: String?) {
                         continuation.resume(Result.failure(MyException("Login onError: $errorCode - $error")) ,null)
                    }

                })
            }
        }catch (e:Exception){
            Result.failure(e)
        }

    }

    override suspend fun getHomeWeatherSketch(homeId:Long,long:Double,lat:Double): Result<WeatherBean> {
       return try {
            suspendCancellableCoroutine { continuation ->
                ThingHomeSdk.newHomeInstance(homeId).getHomeWeatherSketch(long,lat,object:IIGetHomeWetherSketchCallBack {
                    override fun onSuccess(result: WeatherBean?) {
                        result?.let {
                            continuation.resume(Result.success(it), onCancellation = null)
                        } ?: continuation.resume(Result.failure(MyException("Error to get weather info")) ,null)
                    }

                    override fun onFailure(errorCode: String?, errorMsg: String?) {
                        continuation.resume(Result.failure(MyException("Error to get weather info: $errorCode - $errorMsg")) ,null)
                    }

                })
            }
        }catch (e:Exception){
            Result.failure(e)
        }

    }

    override suspend fun getHomeWeatherDetail(
        homeId:Long,
        tempUnit: TempUnit,
        pressureUnit: PressureUnit,
        windSpeedUnit: WindSpeedUnit
    ): Result<List<DashBoardBean>> {

      return  try {
            val units: MutableMap<String, Any> = HashMap()
            units["tempUnit"] = when(tempUnit){
                TempUnit.Celsius -> 1
                TempUnit.Fahrenheit -> 2
            } // °C
            units["pressureUnit"] = when(pressureUnit){
                PressureUnit.HPa -> 1
                PressureUnit.InHg -> 2
                PressureUnit.MmHg -> 3
                PressureUnit.Mb -> 4
            } // hPa
            units["windspeedUnit"] =when(windSpeedUnit){
                WindSpeedUnit.Mph -> 1
                WindSpeedUnit.Ms -> 2
                WindSpeedUnit.Kph -> 3
                WindSpeedUnit.Kmh -> 4
            } // m/s

            suspendCancellableCoroutine { continuation ->
                ThingHomeSdk.newHomeInstance(homeId).getHomeWeatherDetail(10,units,object :IGetHomeWetherCallBack{
                    override fun onSuccess(result: MutableList<DashBoardBean>?) {
                        result?.let {
                            continuation.resume(Result.success(it), onCancellation = null)
                        }
                            ?: continuation.resume(Result.failure(MyException("Error to get weather detail")) ,null)

                    }

                    override fun onFailure(errorCode: String?, errorMsg: String?) {
                        continuation.resume(Result.failure(MyException("Error to get weather info: $errorCode - $errorMsg")) ,null)
                    }

                })
            }
        }catch (e:Exception){
            Result.failure(e)
        }
    }
}