package com.example.horoscopoapp.core

import com.example.horoscopoapp.data.network.HoroscopeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //Dentro del modulo todas las funciones se van a llamar con un agregado de provide

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient{
       val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
       return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit( okHttpClient: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .baseUrl("https://newastro.vercel.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()


    @Provides
    fun provideHoroscopeApi(retrofit: Retrofit): HoroscopeApi {
        return retrofit.create(HoroscopeApi::class.java)
    }
}