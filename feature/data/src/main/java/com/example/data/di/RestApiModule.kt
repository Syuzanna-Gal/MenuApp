package com.example.data.di

import android.content.Context
import com.example.data.BuildConfig
import com.example.data.remote.api.MainApi
import com.example.domain.delegate.CurrentAddressDelegate
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RestApiModule {

    private const val TIMEOUT_CONNECTION_SECONDS = 20L
    private const val TIMEOUT_READ_SECONDS = 20L
    private const val TIMEOUT_WRITE_SECONDS = 20L

    @Provides
    @Singleton
    fun provideRetrofit(
        jsonConfigs: Json
    ): Retrofit = createRetrofit(jsonConfigs)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        cache: Cache,
    ): OkHttpClient = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .followRedirects(true)
        .followSslRedirects(true)
        .connectTimeout(TIMEOUT_CONNECTION_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_READ_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_WRITE_SECONDS, TimeUnit.SECONDS)
        .cache(cache)
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideJsonConfigurations(): Json = Json {
        isLenient = true
        ignoreUnknownKeys = true
        explicitNulls = false
        encodeDefaults = true
    }

    private fun createRetrofit(
        json: Json
    ): Retrofit {

        val converterFactory = json.asConverterFactory("application/json".toMediaType())

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
    }

    private inline fun <reified T> createApiService(
        retrofit: Retrofit,
        okHttpClient: OkHttpClient
    ): T {
        return retrofit.newBuilder().client(okHttpClient).build().create(T::class.java)
    }

    @Singleton
    @Provides
    fun provideCache(@ApplicationContext context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024.toLong() // 10 MB
        val httpCacheDirectory = File(context.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }

    @Singleton
    @Provides
    fun provideMainApi(retrofit: Retrofit, okHttpClient: OkHttpClient): MainApi =
        createApiService(retrofit, okHttpClient)

    @Singleton
    @Provides
    fun provideCurrentAddressDelegate() = CurrentAddressDelegate()
}