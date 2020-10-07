package com.ismin.android

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val createBookActivityRequestCode = 1;
    var bookshelf = Bookshelf();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayBookList()

    }

    fun goToCreation(view: View) {
        val intent = Intent(this, CreateBookActivity::class.java)
        startActivityForResult(intent, this.createBookActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == this.createBookActivityRequestCode){
            bookshelf.addBook(data?.getSerializableExtra("Book") as Book);
        }
        displayBookList()

    }

    fun displayBookList(){
        val bookListFragment = BookListFragment.newInstance(bookshelf.getAllBooks())
        val bookListFragmentTransaction = supportFragmentManager.beginTransaction()
        bookListFragmentTransaction.replace(R.id.relative_layout, bookListFragment)
        bookListFragmentTransaction.commit()

    }


}