package com.hca.hcatask.di


import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hca.hcatask.BuildConfig
import com.hca.hcatask.domain.ApiInterface
import com.hca.hcatask.domain.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_API_URL

    @Singleton
    @Provides
    fun provideNetworkInterceptor(@ApplicationContext app: Context): NetworkConnectionInterceptor =
            NetworkConnectionInterceptor(app)

    @Provides
    @Singleton
    fun provideOkHttpClient(networkConnectionInterceptor: NetworkConnectionInterceptor) = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(networkConnectionInterceptor)
                .build()
    } else OkHttpClient
            .Builder()
            .build()


    fun provideGson(): Gson {
        return GsonBuilder()
                .setLenient()
                .create()
    }


    @Provides
    @Singleton
    fun provideRetrofit(
            okHttpClient: OkHttpClient,
            BASE_URL: String
    ): Retrofit =
            Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(provideGson()))
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)

}