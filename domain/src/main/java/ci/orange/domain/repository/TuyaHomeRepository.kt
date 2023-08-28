package ci.orange.domain.repository

import com.thingclips.smart.home.sdk.api.IThingHomeStatusListener
import com.thingclips.smart.home.sdk.bean.DashBoardBean
import com.thingclips.smart.home.sdk.bean.HomeBean
import com.thingclips.smart.home.sdk.bean.WeatherBean
import com.thingclips.smart.home.sdk.callback.IGetHomeWetherCallBack
import com.thingclips.smart.home.sdk.callback.IIGetHomeWetherSketchCallBack
import com.thingclips.smart.interior.device.confusebean.MQ_802_PushAlarmBean
import com.thingclips.smart.sdk.api.IResultCallback

enum class TempUnit(vl:Int) {
    Celsius(1 ),Fahrenheit(2)
}
enum class PressureUnit(vl:Int){
    HPa(1),
    InHg(2),
    MmHg(3),
    Mb(4)
}
enum class WindSpeedUnit(vl:Int){
    Mph(1),
    Ms(2),
    Kph(3),
    Kmh(4),
}
interface TuyaHomeRepository {
    suspend fun createHome(name:String,lat:Double,long:Double,locationPlace:String,rooms:List<String>):Result<HomeBean>
    suspend fun updateHomeInfo(homeId:Long,name:String,lat:Double,long:Double,locationPlace:String,rooms:List<String>):Result<Boolean>
    suspend fun getHomeList():Result<List<HomeBean>>
    suspend fun getHomeWeatherSketch(homeId:Long,long:Double,lat:Double):Result<WeatherBean>
    suspend fun getHomeWeatherDetail(homeId:Long,tempUnit: TempUnit,pressureUnit: PressureUnit,windSpeedUnit: WindSpeedUnit):Result<List<DashBoardBean>>
}