package com.example.redditpostgrabber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.redditpostgrabber.databinding.ActivityFullTopicBinding

class FullTopicActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullTopicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullTopicBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle(R.string.subreddit_name)

        val title   = binding.titleTextview
        val author  = binding.authorTextview
        val body    = binding.bodyTextview

        val data = intent.getSerializableExtra("topic") as Topic

        title.text  = data.title
        author.text = "posted by ${data.author}"
        body.text   = data.body

    }
}