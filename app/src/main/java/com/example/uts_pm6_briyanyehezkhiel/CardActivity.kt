package com.example.uts_pm6_briyanyehezkhiel

import android.os.Bundle
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CardActivity : AppCompatActivity() {

    private lateinit var cardMessage: TextView
    private lateinit var cardImage: ImageView
    private lateinit var darkModeSwitch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        // Inisialisasi komponen
        cardMessage = findViewById(R.id.card_message)
        cardImage = findViewById(R.id.card_image)
        darkModeSwitch = findViewById(R.id.dark_mode_switch)

        // Mengatur tema gelap untuk teks dan latar belakang
        updateTheme()

        // Mengatur Switch berdasarkan mode saat ini
        darkModeSwitch.isChecked = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                enableDarkMode()
            } else {
                disableDarkMode()
            }
        }

        val message = intent.getStringExtra("MESSAGE")
        if (message != null) {
            cardMessage.text = message
        } else {
            cardMessage.text = getString(R.string.default_message) // Default message if null
        }
    }
    private fun updateTheme() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            cardMessage.setTextColor(ContextCompat.getColor(this, R.color.white))
            window.decorView.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
        } else {
            cardMessage.setTextColor(ContextCompat.getColor(this, R.color.black))
            window.decorView.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        }
    }

    private fun enableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        updateTheme()
    }

    private fun disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        updateTheme()
    }
}

