package com.ismin.android

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class BookViewHolder(rootView: View): RecyclerView.ViewHolder(rootView){
    var textViewTitle : TextView = rootView.findViewById(R.id.textViewTitle)
    var textViewAuthor: TextView = rootView.findViewById(R.id.textViewAuthor)
    var textViewDate: TextView = rootView.findViewById(R.id.textViewDate);

}