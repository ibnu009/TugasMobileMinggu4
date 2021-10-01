package com.ibnu.tugasmobileminggu4.network

import com.ibnu.tugasmobileminggu4.utils.ConstVal.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApp {

    // final public Retrofit fuasas() {return retrofit}
    // val = final, var = tidak final
    // definisi -> Ngebantu kita buat ngambil data dari internet
    // 2 komponen utama + 1 Komponen pembantu
    // 2 Kompunen utama = 1. Retrofit & 2. Services
    // https://api.themoviedb.org/3/movie/top_rated
    // https://api.themoviedb.org/3/ = base url
    // movie/top_rated = end point / service
    // Json, Moshi, dkk
    // fun singkatan dari function
    // Interceptor -> Log, Auto Ngisi Token

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun getMovieService(): MovieService {
        return retrofit.create(MovieService::class.java)
    }

}