import com.example.mobilechallange2023.DataModel.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response

// API Interface
interface MovieApiService {
    @GET("challenge/movies.json")
    fun getMovies(): Call<List<Movie>>
}

fun createRetrofitService(): MovieApiService {
    val httpClient = OkHttpClient.Builder().build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://apps.agentur-loop.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()
    return retrofit.create(MovieApiService::class.java)
}

class MovieViewModel : ViewModel() {
    var movies by mutableStateOf<List<Movie>>(emptyList())
        private set

    var errorMessage by mutableStateOf("")
        private set

    private val service = createRetrofitService()

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            service.getMovies().enqueue(object : Callback<List<Movie>> {
                override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                    if (response.isSuccessful) {
                        movies = response.body() ?: emptyList()
                        Log.d("MovieViewModel", "Movies fetched successfully: ${movies.size} movies")
                    } else {
                        errorMessage = "Error: ${response.message()}"
                        Log.e("MovieViewModel", "Error fetching movies: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                    errorMessage = t.message ?: "Unknown error"
                    Log.e("MovieViewModel", "Error: ${t.message}")
                }
            })
        }
    }
}

@Composable
fun MovieScreen(viewModel: MovieViewModel) {
    if (viewModel.errorMessage.isNotEmpty()) {
        Text("Error: ${viewModel.errorMessage}")
    } else {
        MovieList(viewModel.movies)
    }
}

@Composable
fun MovieList(movies: List<Movie>) {
    Column(modifier = Modifier.padding(16.dp)) {
        movies.forEach { movie ->
            MovieItem(movie)
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = movie.title, style = MaterialTheme.typography.bodyLarge)
        Image(
            painter = rememberAsyncImagePainter(movie.posterUrl),
            contentDescription = "Movie Poster",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview
@Composable
fun MovieApp(viewModel: MovieViewModel = remember { MovieViewModel() }) {
    MovieScreen(viewModel)
}
