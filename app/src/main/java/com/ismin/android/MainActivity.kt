package com.ismin.android

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BookCreator {
    private val createBookActivityRequestCode = 1;
    var bookshelf = Bookshelf();

    private val theLordOfTheRings = Book(
        title = "The Lord of the Rings",
        author = "J. R. R. Tolkien",
        date = "1954-02-15"
    )

    private val theHobbit = Book(
        title = "The Hobbit",
        author = "J. R. R. Tolkien",
        date = "1937-09-21"
    )
    private val aLaRechercheDuTempsPerdu = Book(
        title = "À la recherche du temps perdu",
        author = "Marcel Proust",
        date = "1927"
    );

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.bookshelf.addBook(theLordOfTheRings)
        this.bookshelf.addBook(theHobbit)
        this.bookshelf.addBook(aLaRechercheDuTempsPerdu)


        displayBookList()

    }

    fun goToCreation(view: View) {
        displayCreation()
    }


    fun displayBookList(){
        val bookListFragment = BookListFragment.newInstance(bookshelf.getAllBooks())
        val bookListFragmentTransaction = supportFragmentManager.beginTransaction()
        bookListFragmentTransaction.replace(R.id.frame_layout, bookListFragment)
        bookListFragmentTransaction.commit()
        buttonCreateBook.visibility = View.VISIBLE

    }

    fun displayCreation(){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val createBookFragment = CreateBookFragment()

        fragmentTransaction.replace(R.id.frame_layout, createBookFragment)
        fragmentTransaction.commit()

        buttonCreateBook.visibility = View.GONE
    }

    override fun onBookCreated(book: Book) {
        bookshelf.addBook(book)
        displayBookList()
    }

    override fun closeBookCreation() {
        displayBookList()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_action_clear -> {
                bookshelf.clear()
                displayBookList()
                true
            }
            // If we got here, the user's action was not recognized.
            else -> super.onOptionsItemSelected(item)
        }
    }

}