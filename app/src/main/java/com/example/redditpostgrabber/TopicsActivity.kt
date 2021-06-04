package com.example.redditpostgrabber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.redditpostgrabber.databinding.ActivityTopicsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TopicsActivity : AppCompatActivity(), RecyclerViewClickInterface {
    private lateinit var binding: ActivityTopicsBinding
    var list: ArrayList<Topic> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopicsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle(R.string.subreddit_name)

        val recyclerView = binding.topisReciclerview
        val progressBar = binding.indeterminateBar

        // configuração do recyclerview
        recyclerView.addItemDecoration(DividerItemDecoration(this,
            DividerItemDecoration.VERTICAL)
        )

        // obtenção dos dados da api e tratamento json
        retrieveJsonData(recyclerView, progressBar)

    }

    /*
     * Requisita via HTTP os dados na API e realiza o parsing para objetos Kotlin
     */
    private fun retrieveJsonData(recyclerView: RecyclerView, progressBar: ProgressBar) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.reddit.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(APIService::class.java)

        val call = service.getTopic()
        progressBar.visibility = View.VISIBLE

        call.enqueue(object : Callback<Base> {

            override fun onResponse(call: Call<Base>, response: Response<Base>) {
                if (response.isSuccessful) {
                    progressBar.visibility = View.GONE
                    response.body()?.data?.children?.forEach { i ->
                        list.add(
                            Topic(
                                i.topic.title,
                                i.topic.author,
                                i.topic.body
                            )
                        )
                    }
                    recyclerView.adapter = RecyclerAdapter(
                        list,
                        this@TopicsActivity
                    )
                }
            }

            override fun onFailure(call: Call<Base>, t: Throwable) {
                progressBar.visibility = View.GONE
                Log.e("Response Error", t.message.toString())
            }

        })

    }

    // lança a tela do post completo
    override fun onClick(position: Int) {
        startActivity(Intent(this, FullTopicActivity::class.java)
            .putExtra("topic", list[position]))
    }
}