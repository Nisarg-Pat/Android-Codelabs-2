package com.example.a024bookshelf.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a024bookshelf.MainApplication
import com.example.a024bookshelf.data.repository.BookRepository
import com.example.a024bookshelf.model.Book
import com.example.a024bookshelf.model.BookItem
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface UIState {
    data class Success(val books: List<Book>): UIState
    object Error: UIState
    object Loading: UIState
}

class BookShelfViewModel(private val bookRepository: BookRepository): ViewModel() {
    var uiState: UIState by mutableStateOf(UIState.Loading)
        private set

    init {
        getBooks()
    }

    private fun getBooks() {
        viewModelScope.launch {
            uiState = try {
                val bookItems: List<BookItem> = bookRepository.searchBooks("jazz+history")
                val bookList: MutableList<Book> = mutableListOf()
                for(bookItem in bookItems) {

                    val book = bookRepository.getBookFromID(bookItem.id)
                    bookList.add(book)
                }
                UIState.Success(bookList)
            } catch (e: IOException) {
                UIState.Error
            } catch (e: HttpException) {
                UIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MainApplication)
                val bookRepository = application.container.bookRepository
                BookShelfViewModel(bookRepository)
            }
        }
    }

}
