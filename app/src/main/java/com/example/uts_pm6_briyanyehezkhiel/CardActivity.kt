package com.example.uts_pm6_briyanyehezkhiel

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.SwitchCompat
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager

class CardActivity : AppCompatActivity() {

    private lateinit var senderName: TextView // TextView untuk nama pengirim
    private lateinit var messageDetail: TextView // TextView untuk detail pesan
    private lateinit var targetName: TextView // TextView untuk nama
    private lateinit var senderName1: TextView
    private lateinit var messageDetail1: TextView
    private lateinit var targetName1: TextView
    private lateinit var cardImage: ImageView
    private lateinit var darkModeSwitch: SwitchCompat
    private lateinit var toolbar: Toolbar
    private lateinit var nameApp: TextView
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        // Inisialisasi komponen
        senderName = findViewById(R.id.user_name) // TextView untuk nama pengirim
        messageDetail = findViewById(R.id.message_detail) // TextView untuk detail pesan
        targetName = findViewById(R.id.target_name) // TextView untuk nama
        senderName1 = findViewById(R.id.sender_name)
        messageDetail1 = findViewById(R.id.message_detail1)
        targetName1 = findViewById(R.id.target_name_details)
        cardImage = findViewById(R.id.card_image)
        darkModeSwitch = findViewById(R.id.dark_mode_switch)
        toolbar = findViewById(R.id.toolbar)
        nameApp = findViewById(R.id.nameApp)

        // Inisialisasi SharedPreferences
        preferences = PreferenceManager.getDefaultSharedPreferences(this)

        // Mengatur tema sesuai mode saat ini
        val isDarkMode = preferences.getBoolean("DARK_MODE", false)
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )
        updateTheme()

        // Mengatur Switch berdasarkan mode saat ini
        darkModeSwitch.isChecked = isDarkMode
        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            setDarkMode(isChecked)
        }

        // Menampilkan data dari intent
        val sender = intent.getStringExtra("SENDER_NAME") ?: getString(R.string.default_message)
        val message = intent.getStringExtra("MESSAGE_HINT") ?: getString(R.string.default_message)
        val target = intent.getStringExtra("TARGET_NAME") ?: getString(R.string.default_message)

        senderName.text = sender
        messageDetail.text = message
        targetName.text = target
        senderName1.text = sender
        messageDetail1.text = message
        targetName1.text = target
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        updateTheme()
    }

    private fun setDarkMode(isEnabled: Boolean) {
        if (isEnabled != preferences.getBoolean("DARK_MODE", false)) {
            preferences.edit().putBoolean("DARK_MODE", isEnabled).apply()
            AppCompatDelegate.setDefaultNightMode(
                if (isEnabled) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            )
            updateTheme()
        }
    }

    private fun updateTheme() {
        val isDark = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
        val backgroundColor = if (isDark) R.color.black else R.color.white
        val textColor = if (isDark) R.color.white else R.color.black

        toolbar.setBackgroundColor(ContextCompat.getColor(this, backgroundColor))
        toolbar.setTitleTextColor(ContextCompat.getColor(this, textColor))
        nameApp.setTextColor(ContextCompat.getColor(this, textColor))
        senderName.setTextColor(ContextCompat.getColor(this, textColor))
        messageDetail.setTextColor(ContextCompat.getColor(this, textColor))
        targetName.setTextColor(ContextCompat.getColor(this, textColor))
    }
}
