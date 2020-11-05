package com.ismin.android

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), BookCreator {
    private val createBookActivityRequestCode = 1;
    var bookshelf = Bookshelf();

    private lateinit var bookService: BookService



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://bookshelf-ebs.cleverapps.io")
            .build()

        bookService= retrofit.create(BookService::class.java)

        bookService.getAllBooks().enqueue(object : Callback<ArrayList<Book>> {
            override fun onResponse(
                call: Call<ArrayList<Book>>,
                response: Response<ArrayList<Book>>
            ) {
                val allBooks= response.body()
                allBooks?.forEach{
                    bookshelf.addBook(it)
                }
                val bookListFragment = BookListFragment.newInstance(bookshelf.getAllBooks())
                supportFragmentManager.beginTransaction()
                    .add(R.id.frame_layout, bookListFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit()
            }

            override fun onFailure(call: Call<ArrayList<Book>>, t: Throwable) {
                displayErrorToast(t)
        }
        }
        )


    }

    private fun displayErrorToast(t: Throwable) {
        Toast.makeText(applicationContext, "Network error ${t.localizedMessage}", Toast.LENGTH_LONG)
            .show()
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
        bookService.createBook(book).enqueue(object : Callback<Book> {
            override fun onResponse(call: Call<Book>, response: Response<Book>) {
                bookshelf.addBook(response.body()!!)
                displayBookList()
            }

            override fun onFailure(call: Call<Book>, t: Throwable) {
                displayErrorToast(t)
            }
        })
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