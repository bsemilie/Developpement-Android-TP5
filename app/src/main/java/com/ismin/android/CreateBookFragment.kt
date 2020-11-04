package com.ismin.android

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.widget.Button
import android.widget.EditText



class CreateBookFragment : Fragment() {

    private lateinit var listener: BookCreator


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_create_book, container, false)
        rootView.findViewById<Button>(R.id.buttonSave).setOnClickListener{
            val edtTitle = rootView.findViewById<EditText>(R.id.edtBookTitle)
            val title = edtTitle.text.toString();

            val edtAuthor = rootView.findViewById<EditText>(R.id.edtBookAuthor);
            val author = edtAuthor.text.toString();

            val edtDate = rootView.findViewById<EditText>(R.id.edtBookDate);
            val date = edtDate.text.toString();
            val book = Book(title, author, date)
            listener.onBookCreated(book)
        }

        ObjectAnimator.ofFloat(rootView.findViewById(R.id.constraintLayout), "translationY", 300f, 0f)
            .apply{
                interpolator = BounceInterpolator()
                duration = 5000
                start()
            }
        return rootView
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BookCreator) {
            listener = context
        } else {
            throw RuntimeException("$context must implement BookCreator")
        }
    }

}

interface BookCreator {
    fun onBookCreated(book: Book)
    fun closeBookCreation()
}