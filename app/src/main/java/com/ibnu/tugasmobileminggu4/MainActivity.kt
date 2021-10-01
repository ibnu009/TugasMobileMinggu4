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

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMovie.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        RetrofitApp().getMovieService().getTopRatedMovies(API_KEY)
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    val data = response.body()
                    val movies = data?.results
                    binding.rvMovie.adapter = MovieAdapter(data?.results!!, this@MainActivity)
                    val listName = ArrayList<String>()

                    for (i in 0 until movies?.size!!){
                        listName.add(movies[i].title)
                    }
                    val arrayAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1,
                    listName)
                    binding.autoCompleteMovie.setAdapter(arrayAdapter)
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d("Main", "${t.printStackTrace()}")
                }
            })
    }

    override fun onSpinnerClicked(movieName: String, actionName: String) {
        Toast.makeText(this, "test $movieName telah di$actionName", Toast.LENGTH_SHORT).show()
    }

}