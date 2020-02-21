package com.nagarro.todoapp

import android.content.Context
import com.nagarro.todoapp.data.network.NetworkInterceptor
import com.nagarro.todoapp.data.network.ToDoListApi
import com.nagarro.todoapp.data.repository.DataRepositoryImpl
import com.nagarro.todoapp.data.repository.IDataRepository
import com.nagarro.todoapp.ui.mainlist.MainListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val koinModule = module {
    single { DataRepositoryImpl(get()) as IDataRepository }
    viewModel { MainListViewModel(get()) }
    factory { provideOkHttpClient(get(), androidContext()) }
    factory { provideApi(get()) }
    factory { httpLoggingInterceptor() }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(
    authInterceptor: HttpLoggingInterceptor,
    androidContext: Context
): OkHttpClient {
    return OkHttpClient().newBuilder()
        .connectTimeout(5, TimeUnit.MINUTES)
        .writeTimeout(2, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES)
        .addInterceptor(authInterceptor)
        .addInterceptor(NetworkInterceptor(androidContext))
        .build()
}

fun provideApi(retrofit: Retrofit): ToDoListApi = retrofit.create(ToDoListApi::class.java)

fun httpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return httpLoggingInterceptor
}


