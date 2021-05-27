package com.example.redditpostgrabber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class TopicsActivity : AppCompatActivity() {
    var list: ArrayList<Topic> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topics)
        setTitle(R.string.topics_activity_title)

        val recyclerView: RecyclerView = findViewById(R.id.topis_reciclerview)
        val progressBar: ProgressBar = findViewById(R.id.indeterminateBar)

        // configuração do recyclerview
        recyclerView.addItemDecoration(DividerItemDecoration(this,
            DividerItemDecoration.VERTICAL)
        )

        // obtenção dos dados da api e tratamento json
        retrieveJsonData(recyclerView, progressBar)

    }


    private fun retrieveJsonData(recyclerView: RecyclerView, progressBar: ProgressBar) {
        val url = "https://www.reddit.com/r/androiddev.json?raw_json=1"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("JSON Data Retrieve", e.toString())
            }

            // parsing dos dados da api para o kotlin
            override fun onResponse(call: Call, response: Response) {

                runOnUiThread {
                    progressBar.visibility = View.VISIBLE
                }

                val body = response.body!!.string()
                val json = GsonBuilder().create()
                    .fromJson(body, Base::class.java)
                val apiChildrenArray = json.data.children
                for  (i in apiChildrenArray) {
                    list.add(  // salvando infos do tópico na lista
                        Topic(
                            i.topic.title,
                            i.topic.author
                        )
                    )
                    Log.i("Topic title", i.topic.title)
                }
                // atualiza o adapter do recyclerview na thread principal
                runOnUiThread {
                    progressBar.visibility = View.GONE
                    recyclerView.adapter = RecyclerAdapter(list)
                }
            }
        })
    }

}