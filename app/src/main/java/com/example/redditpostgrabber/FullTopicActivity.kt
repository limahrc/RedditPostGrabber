package com.example.redditpostgrabber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class FullTopicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_topic)
        setTitle(R.string.subreddit_name)

        val title:  TextView = findViewById(R.id.title_textview)
        val author: TextView = findViewById(R.id.author_textview)
        val body:   TextView = findViewById(R.id.body_textview)

        val data = intent.getSerializableExtra("topic") as Topic

        title.text = data.title
        author.text = "posted by ${data.author}"
        body.text = data.body

    }
}