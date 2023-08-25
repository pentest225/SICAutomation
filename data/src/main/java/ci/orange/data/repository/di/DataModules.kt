package ci.orange.data.repository.di


import android.app.Application
import ci.orange.data.repository.TuyaAuthRepositoryImpl
import ci.orange.domain.repository.TuyaAuthRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModules {


    @Provides
    @Singleton
    fun provideTuyaAuthRepository(): TuyaAuthRepository {
        return TuyaAuthRepositoryImpl()
    }
}