package com.example.currencytracker.di.modules

import com.example.currencytracker.BuildConfig
import com.example.currencytracker.network.calladapter.HttpResultCallAdapterFactory
import com.example.currencytracker.network.interceptor.HttpInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideHttpResultCallAdapterFactory() : CallAdapter.Factory {
        return HttpResultCallAdapterFactory()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @Named("LoggingInterceptor") loggingInterceptor: Interceptor,
        @Named("HttpInterceptor") httpInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpInterceptor)
            .addInterceptor(loggingInterceptor)
            .readTimeout(BuildConfig.READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(BuildConfig.WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
            .connectTimeout(BuildConfig.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConverter(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Named("LoggingInterceptor")
    @Provides
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Named("HttpInterceptor")
    @Provides
    fun provideHttpInterceptor(): Interceptor {
        return HttpInterceptor()
    }
}