package com.example.uts_pm6_briyanyehezkhiel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var messageInput: EditText
    private lateinit var createCardButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageInput = findViewById(R.id.message_input)
        createCardButton = findViewById(R.id.create_card_button)

        createCardButton.setOnClickListener {
            val message = messageInput.text.toString()
            if (message.isNotEmpty()) {
                val intent = Intent(this, CardActivity::class.java).apply {
                    putExtra("MESSAGE", message)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Pesan tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
