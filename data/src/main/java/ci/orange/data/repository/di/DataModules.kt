package ci.orange.data.repository.di


import android.app.Application
import ci.orange.data.repository.TuyaAuthRepositoryImpl
import ci.orange.data.repository.TuyaHomeRepositoryImpl
import ci.orange.domain.repository.TuyaAuthRepository
import ci.orange.domain.repository.TuyaHomeRepository

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

    @Provides
    @Singleton
        fun provideTuyaHomeRepository(): TuyaHomeRepository {
        return TuyaHomeRepositoryImpl()
    }
}