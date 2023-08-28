package ci.orange.data.repository

import ci.orange.domain.model.MyException
import ci.orange.domain.repository.TuyaAuthRepository
import com.thingclips.smart.android.user.api.ILoginCallback
import com.thingclips.smart.android.user.api.IRegisterCallback
import com.thingclips.smart.android.user.bean.User
import com.thingclips.smart.home.sdk.ThingHomeSdk
import com.thingclips.smart.sdk.api.IResultCallback
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException


class TuyaAuthRepositoryImpl : TuyaAuthRepository {
    override fun getConnectedUser(): User = ThingHomeSdk.getUserInstance().user!!
    override suspend fun loginWithPhoneAndPassword(
        countryCode: String,
        phoneNumber: String,
        password: String
    ): Result<User> {
        return try {
            suspendCancellableCoroutine { continuation ->
                ThingHomeSdk.getUserInstance().loginWithPhonePassword(
                    countryCode,
                    phoneNumber,
                    password,
                    object : ILoginCallback {
                        override fun onSuccess(user: User?) {
                            user?.let {
                                continuation.resume(Result.success(it), onCancellation = null)
                            }
                                ?: continuation.resume(Result.failure(MyException("Login onSuccess returned null user")) ,null)
                        }

                        override fun onError(p0: String?, p1: String?) {
                            continuation.resume(Result.failure(MyException("Login onError: $p0 - $p1")) ,null)
                        }
                    })
            }
        }catch (e:Exception){
            Result.failure(e)
        }
    }

    override suspend fun loginWithPhoneAndCodeVerification(
        countryCode: String,
        phoneNumber: String,
        code: String
    ) {

    }

    override suspend fun sendVerificationCode(
        userName: String,
        region: String,
        countryCode: String,
        type: Int
    ): Result<Boolean> {
        return try {
            suspendCancellableCoroutine { continuation ->
                ThingHomeSdk.getUserInstance()
                    .sendVerifyCodeWithUserName(
                        userName,
                        region,
                        countryCode,
                        type,
                        object : IResultCallback {
                            override fun onError(code: String?, error: String?) {
                                return continuation.resume(Result.failure(MyException("Sending error $error")),null)
                            }

                            override fun onSuccess() {
                                return continuation.resume(Result.success(true), null)
                            }
                        })
            }
        }catch (e:Exception){
            Result.failure(MyException("Sending error $e"))
        }
    }


    override suspend fun verifyCode(
        userName: String,
        region: String,
        countryCode: String,
        code: String,
        actionType: Int
    ): Result<Boolean> {
        return try {
            suspendCancellableCoroutine { continuation ->
                ThingHomeSdk.getUserInstance().checkCodeWithUserName(
                    userName,
                    region,
                    countryCode,
                    code,
                    actionType,
                    object : IResultCallback {
                        override fun onError(code: String?, error: String?) {
                            continuation.resumeWithException(Exception("Error verification code :$code -$error"))
                        }

                        override fun onSuccess() {
                            continuation.resume(Result.success(true), null)
                        }
                    })
            }
        }catch (e:Exception){
            Result.failure(MyException("Verification error $e"))
        }

    }

    override suspend fun registerWithPhoneNumber(
        countryCode: String,
        phoneNumber: String,
        passwd: String,
        code: String
    ):Result<User> {
        return  try {
            suspendCancellableCoroutine{ continuation ->
                ThingHomeSdk.getUserInstance().registerAccountWithPhone(countryCode,phoneNumber,passwd,code,object :IRegisterCallback {
                    override fun onSuccess(user: User?) {
                        user?.let {
                            continuation.resume(Result.success(it),null)
                        }?: continuation.resume(Result.failure(MyException("User is null")),null)
                    }
                    override fun onError(p0: String?, p1: String?) {
                        continuation.resume(Result.failure(MyException("User phone register error ")),null)
                    }
                })
            }
        }catch (e:Exception){
            Result.failure(MyException("Register error $e"))
        }
    }

    override suspend fun resetAccountPassword(
        countryCode: String,
        phoneNumber: String,
        passwd: String,
        code: String,
        newPassword: String
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun userIsAuth(): Result<Boolean>  = Result.success(ThingHomeSdk.getUserInstance().isLogin)
    override suspend fun loginWithEmailAndPassword(
        countryCode: String,
        email: String,
        password: String,
        verificationCode: String
    ): Result<User> {
        return  try {
            suspendCancellableCoroutine{ continuation ->
                ThingHomeSdk.getUserInstance().loginWithEmail(countryCode,email,password,object :ILoginCallback {
                    override fun onSuccess(user: User?) {
                        user?.let {
                            continuation.resume(Result.success(it),null)
                        }?: continuation.resume(Result.failure(MyException("User is null")),null)
                    }

                    override fun onError(p0: String?, p1: String?) {
                        continuation.resume(Result.failure(MyException("Login error : $p0,$p1")),null)
                    }

                })
            }
        }catch (e:Exception){
            Result.failure(MyException("Register error $e"))
        }
    }

    override suspend fun registerWithEmailAndPassword(
        countryCode: String,
        email: String,
        password: String,
        verificationCode: String
    ): Result<User> {
        return  suspendCancellableCoroutine{ continuation ->
            ThingHomeSdk.getUserInstance().registerAccountWithEmail(countryCode,email,password,verificationCode,object :IRegisterCallback {
                override fun onSuccess(user: User?) {
                    user?.let {
                        continuation.resume(Result.success(it),null)
                    }?: continuation.resume(Result.failure(MyException("User is null")),null)
                }

                override fun onError(p0: String?, p1: String?) {
                    continuation.resume(Result.failure(MyException("Register error : $p0,$p1")),null)
                }

            })
        }
    }

}