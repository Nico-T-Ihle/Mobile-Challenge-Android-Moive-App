import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun TiltedGrid(viewModel: MovieViewModel) {
    val gridState = rememberLazyGridState()

    LaunchedEffect(viewModel.movies) {
        val totalItems = viewModel.movies.size
        if (totalItems >= 2) {
            gridState.scrollToItem(totalItems - 2)
        }
    }
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 90.dp),
        state = gridState,
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(viewModel.movies) { movie ->
            TiltedBox(movie.posterUrl)
        }
    }
}

@Composable
fun TiltedBox(title: String) {
    val rotationAngle = 10f

    Box(
        modifier = Modifier
            .size(120.dp, 145.dp)
            .graphicsLayer(rotationZ = -rotationAngle)
    ) {
        Image(
            painter = rememberAsyncImagePainter(title),
            contentDescription = "Movie Poster",
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
        )
    }
}

@Preview
@Composable
fun PreviewTiltedGrid() {
    TiltedGrid(viewModel = MovieViewModel())
}
