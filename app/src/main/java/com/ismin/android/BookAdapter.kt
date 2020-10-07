package com.ismin.android


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(private val books: ArrayList<Book>): RecyclerView.Adapter<BookViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_book, parent, false)
        return BookViewHolder(row)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val (title, author, date) = this.books[position]
        holder.textViewAuthor.text = author
        holder.textViewTitle.text = title
        holder.textViewDate.text = date
    }

    fun refreshBooks(newBooks: ArrayList<Book>){
        this.books.clear()
        this.books.addAll(newBooks)
        this.notifyDataSetChanged()
    }


}