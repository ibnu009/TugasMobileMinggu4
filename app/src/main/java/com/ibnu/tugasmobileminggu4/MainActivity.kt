package com.ibnu.tugasmobileminggu4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibnu.tugasmobileminggu4.databinding.ActivityMainBinding
import com.ibnu.tugasmobileminggu4.network.MovieResponse
import com.ibnu.tugasmobileminggu4.network.RetrofitApp
import com.ibnu.tugasmobileminggu4.utils.ConstVal.API_KEY
import com.ibnu.tugasmobileminggu4.utils.ItemClickHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), ItemClickHandler {

    // menggunakan bantuan view-binding agar tidak perlu memanggil findviewbyid berulang kali
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // mengeset jenis layout manager, pada kasus ini adalah list
        binding.rvMovie.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //pemanggilan API dengan method enqueue
        RetrofitApp().getMovieService().getTopRatedMovies(API_KEY)
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    // mendapat data retrofit dengan memanggil object response
                    val data = response.body()
                    val movies = data?.results
                    //Kita memasukkan data pada parameter yang ada pada Recyclerview
                    binding.rvMovie.adapter = MovieAdapter(data?.results!!, this@MainActivity)

                    //listName hanya sebagai penampung untuk data name dari movie
                    val listName = ArrayList<String>()
                    for (i in 0 until movies?.size!!){
                        listName.add(movies[i].title)
                    }
                    // Set array adapter dengan arrylist yang telah kita buat tadi pada baris 45
                    val arrayAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1,
                    listName)
                    binding.autoCompleteMovie.setAdapter(arrayAdapter)
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d("Main", "${t.printStackTrace()}")
                }
            })
    }

    // Memberikan action pada item dengan pemanfaatan interface
    override fun onSpinnerClicked(movieName: String, actionName: String) {
        Toast.makeText(this, "test $movieName telah di$actionName", Toast.LENGTH_SHORT).show()
    }

}