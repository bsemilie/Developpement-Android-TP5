package com.ismin.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import java.util.*

class CreateBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_book)

        val buttonSave = findViewById<Button>(R.id.buttonSave)
        buttonSave.setOnClickListener{
           val book = createBook();
            val intent = Intent(this, MainActivity::class.java);
            intent.putExtra("Book", book);
            setResult(1,intent);
            finish();



        }
    }

    fun createBook(): Book{
        val edtTitle = findViewById<EditText>(R.id.editTextTitle);
        val title = edtTitle.text.toString();

        val edtAuthor = findViewById<EditText>(R.id.editTextAuthor);
        val author = edtAuthor.text.toString();

        val edtDate = findViewById<EditText>(R.id.editTextDate);
        val date = edtDate.text.toString();

        return(Book(title, author, date));
    }
}