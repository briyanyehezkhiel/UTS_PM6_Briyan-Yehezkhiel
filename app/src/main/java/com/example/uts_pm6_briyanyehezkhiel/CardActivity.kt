package com.example.uts_pm6_briyanyehezkhiel

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CardActivity : AppCompatActivity() {

    private lateinit var cardMessage: TextView
    private lateinit var cardImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        cardMessage = findViewById(R.id.card_message)
        cardImage = findViewById(R.id.card_image)

        val message = intent.getStringExtra("MESSAGE")
        if (message != null) {
            cardMessage.text = message
        } else {
            cardMessage.text = getString(R.string.default_message) // Default message if null
        }
    }
}

