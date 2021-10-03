package com.ibnu.tugasmobileminggu4.network

// class model untuk menerima response dari JSON
data class MovieResponse(
    val page: Int,
    val results: ArrayList<Movie>
)
