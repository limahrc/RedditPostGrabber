package com.example.redditpostgrabber

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val topicList: List<Topic>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var counter = 0  //contador de views criadas

    // recuperação de elementos gráficos do layout
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.topic_title_text)
        val subtitle: TextView = itemView.findViewById(R.id.topic_sub_text)
    }

    // inflagem dos elementos gráficos e atribuição de lógica
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        Log.i("RecyclerAdapter","Views Criadas: ${counter++}")
        return ViewHolder(inflater.inflate(R.layout.row_item, parent, false))
    }

    // exibição das informações nos elementos da lista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = topicList[position].title
        holder.subtitle.text = topicList[position].author
    }

    override fun getItemCount(): Int {
        return topicList.size
    }



}