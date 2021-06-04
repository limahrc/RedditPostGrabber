package com.example.redditpostgrabber

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.redditpostgrabber.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ações tomadas ao clique do botão
        binding.button.setOnClickListener {

                // lançamento da nova tela
                startActivity(
                    Intent(
                        this,
                        TopicsActivity::class.java
                    )
                )
            }
    }
}

