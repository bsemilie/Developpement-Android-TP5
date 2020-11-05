package com.ismin.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ismin.android.R

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val BOOKS = "books"


class BookListFragment : Fragment() {

    private lateinit var books: ArrayList<Book>

    lateinit var bookAdapter: BookAdapter
    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        books = arguments!!.getSerializable(BOOKS) as ArrayList<Book>


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_book_list, container, false)
        recyclerView = view.findViewById<RecyclerView>(R.id.f_list_book)
        bookAdapter = BookAdapter(books)
        this.recyclerView.adapter = bookAdapter
        val linearLayoutManager = LinearLayoutManager(context)
        this.recyclerView.layoutManager = linearLayoutManager

        val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
        this.recyclerView.addItemDecoration(dividerItemDecoration)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(books: ArrayList<Book>): BookListFragment{
            val bundle = Bundle();
            bundle.putSerializable(BOOKS, books)
            val bookListFragment = BookListFragment();
            bookListFragment.arguments = bundle;
            return bookListFragment;
            }
    }
}