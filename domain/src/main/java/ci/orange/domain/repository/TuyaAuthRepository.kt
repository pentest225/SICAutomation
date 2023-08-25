package ci.orange.domain.repository

import com.thingclips.smart.android.user.bean.User

interface TuyaAuthRepository {
    suspend fun loginWithPhoneAndPassword(countryCode:String= "225",phoneNumber: String,password:String):Result<User>
    suspend fun loginWithPhoneAndCodeVerification(countryCode:String= "",phoneNumber: String,code:String)
    suspend fun sendVerificationCode(userName: String,region:String ="",countryCode:String = "225",type: Int):Result<Boolean>
    suspend fun verifyCode(userName:String,region:String = "",countryCode:String = "225" ,code:String,actionType:Int):Result<Boolean>
    suspend fun registerWithPhoneNumber(countryCode:String = "255",phoneNumber:String,passwd:String,code:String):Result<User>
    suspend fun resetAccountPassword(countryCode:String = "225",phoneNumber:String,passwd:String,code:String,newPassword:String)
    suspend fun userIsAuth():Result<Boolean>
    suspend fun loginWithEmailAndPassword(countryCode: String = "225",email:String,password:String,verificationCode:String):Result<User>
    suspend fun registerWithEmailAndPassword(countryCode: String = "225",email:String,password:String,verificationCode:String):Result<User>
}