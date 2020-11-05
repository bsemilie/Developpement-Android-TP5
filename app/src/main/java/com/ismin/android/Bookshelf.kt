package com.ismin.android

import java.time.LocalDate

class Bookshelf {

    private val bookshelf: ArrayList<Book> = arrayListOf<Book>();



    fun addBook(book: Book) {
        if (this.bookshelf.isEmpty()) {
            this.bookshelf.add(book);
        } else {
            for (i in 0 until this.bookshelf.size) {
                if (this.bookshelf[i].title.equals(book.title)) {
                    return;
                }
            }
            this.bookshelf.add(book);
        }
    }

    fun getBook(title: String): Book? {

       return this.bookshelf.find{it.title == title};
    }




    fun getAllBooks(): ArrayList<Book> {
        this.bookshelf.sortBy { it.title };
        return (this.bookshelf);
    }



    fun getBooksOf(author: String): List<Book?> {
        val lclBookshelf = this.bookshelf.filter {it.author == author };
        return lclBookshelf;
    }


    fun getTotalNumberOfBooks(): Int {
        return this.bookshelf.size;
    }



    fun getBooksPublishedBefore(adate: String): List<Book?> {
        val lclBookshelf = this.bookshelf.filter { it.date <= adate };
        return lclBookshelf;
    }

    fun clear(): Unit {
        this.bookshelf.clear()
    }
}
