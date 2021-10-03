package com.ibnu.tugasmobileminggu4.network

import com.ibnu.tugasmobileminggu4.utils.ConstVal.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApp {

    /* variable client yang isinya adalah OKHTTP untuk melakukan debugging dengan level body untuk
     mengetahui isi dari JSON yang didapat */

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    // Komponen utama dalam pemanggilan API dengan retrofit adalah class retrofit itu sendiri
    private val retrofit = Retrofit.Builder()
            // BASE_URL didapat dari class ConstVal
        .baseUrl(BASE_URL)
            // Converter dari jenis API yang digunakan, dalam kasus ini menggunakan JSON
        .addConverterFactory(GsonConverterFactory.create())
            /* Client untuk bantuan ketika pemanggilan API, dalam kasus ini penjelasan ada di baris
             ada di baris nomor 11 */
        .client(client)
        .build()

    // Pemanggilan end-points
    fun getMovieService(): MovieService {
        return retrofit.create(MovieService::class.java)
    }

}