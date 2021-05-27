package com.example.redditpostgrabber

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getTopicsButton = findViewById<Button>(R.id.button)

        getTopicsButton.setOnClickListener { // ações tomadas ao clique do botão

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

