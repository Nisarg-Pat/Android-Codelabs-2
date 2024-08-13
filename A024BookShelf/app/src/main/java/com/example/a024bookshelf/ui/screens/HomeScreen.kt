package com.example.a024bookshelf.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.a024bookshelf.R
import com.example.a024bookshelf.model.Book
import com.example.a024bookshelf.model.ImageLink
import com.example.a024bookshelf.model.VolumeInfo
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun HomeScreen(
    uiState: UIState,
    modifier: Modifier = Modifier
) {
    when(uiState) {
        is UIState.Loading -> LoadingScreen(modifier)
        is UIState.Success -> BookScreen(uiState.books, modifier)
        is UIState.Error -> ErrorScreen( modifier)
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Text(text = stringResource(id = R.string.loading), modifier = modifier)
}

@Composable
fun BookScreen(
    books: List<Book>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = books, key = {book -> book.id}) { book ->
            BookCard(book)
        }
    }

}

@Composable
fun BookCard(
    book: Book,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(300.dp),
        shape = RectangleShape
    ) {
        val imageURL = book.volumeInfo.imageLinks.thumbnail.replace("http", "https")
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(imageURL)
                .crossfade(true)
                .build(),
            contentDescription = book.id,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier
) {
    Text(text = stringResource(id = R.string.error), modifier = modifier)
}

@Preview
@Composable
fun BookCardPreview() {
    BookCard(Book("1", VolumeInfo( ImageLink("http://books.google.com/books/content?id=J9G50L3c14QC&printsec=frontcover&img=1&zoom=1&edge=curl&imgtk=AFLRE73JkjjXGo3SWHGAiMc49P6rRHYy_cFZbTKVM7FjBEG63r_IkjOKTuQ3QrIH8UmeZNKvyaaGC3o4YLyQRKS9ex7jX7arbV-g-cTln1lY0trx4n-alsV5Nna5nPRchM4_mSeoMVEF&source=gbs_api"))))
}