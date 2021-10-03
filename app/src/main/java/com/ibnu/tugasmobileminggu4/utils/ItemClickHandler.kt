package com.ibnu.tugasmobileminggu4.utils

// untuk menghandle semua tindakan yang didapat ketika melakukan click pada recyclerview
interface ItemClickHandler {
    fun onSpinnerClicked(movieName: String, actionName: String)
}