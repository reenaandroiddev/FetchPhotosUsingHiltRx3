package com.learn.fetchphotos.di

import com.learn.fetchphotos.BuildConfig
import com.learn.fetchphotos.data.remote.AlbumPhotosApi
import com.learn.fetchphotos.data.repository.AlbumRepositoryImpl
import com.learn.fetchphotos.domain.repository.AlbumPhotosRepository
import com.learn.fetchphotos.domain.usecases.GetAlbumPhotosUseCase
import com.learn.fetchphotos.domain.usecases.GetAlbumPhotosUseCaseImpl
import com.learn.fetchphotos.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun provideHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    } else {
        OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create(AlbumPhotosApi::class.java)

    @Provides
    fun provideRepository(api: AlbumPhotosApi): AlbumPhotosRepository {
        return AlbumRepositoryImpl(api)
    }

    @Provides
    fun provideUseCase(repository: AlbumPhotosRepository): GetAlbumPhotosUseCase {
        return GetAlbumPhotosUseCaseImpl(repository)
    }


}