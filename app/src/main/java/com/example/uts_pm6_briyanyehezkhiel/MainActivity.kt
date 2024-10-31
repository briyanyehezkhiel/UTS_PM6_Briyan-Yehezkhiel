package com.example.uts_pm6_briyanyehezkhiel

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager

class MainActivity : AppCompatActivity() {

    private lateinit var messageHint: EditText
    private lateinit var senderNameInput: EditText // EditText untuk nama pengirim
    private lateinit var targetNameInput: EditText // EditText untuk nama tujuan
    private lateinit var createCardButton: Button
    private lateinit var darkModeSwitch: SwitchCompat
    private lateinit var toolbar: Toolbar
    private lateinit var nameApp: TextView
    private lateinit var mainLayout: View
    private lateinit var title: TextView



    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Menginisialisasi komponen
        messageHint = findViewById(R.id.message_hint)
        senderNameInput = findViewById(R.id.sender_name_input) // Inisialisasi EditText untuk nama pengirim
        targetNameInput = findViewById(R.id.target_name_input) // Inisialisasi EditText untuk nama tujuan
        createCardButton = findViewById(R.id.create_card_button)
        darkModeSwitch = findViewById(R.id.dark_mode_switch)
        toolbar = findViewById(R.id.toolbar)
        nameApp = findViewById(R.id.nameApp)
        mainLayout = findViewById(R.id.mainlayout)
        title = findViewById(R.id.title)

        // Inisialisasi SharedPreferences
        preferences = PreferenceManager.getDefaultSharedPreferences(this)

        // Mengatur toolbar sebagai ActionBar
        setSupportActionBar(toolbar)

        // Mengatur tema sesuai dengan dark mode atau light mode
        val isDarkMode = preferences.getBoolean("DARK_MODE", false)
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )
        updateTheme()

        // Mengatur Switch sesuai mode saat ini
        darkModeSwitch.isChecked = isDarkMode
        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            setDarkMode(isChecked)
        }

        createCardButton.setOnClickListener {
            val message = messageHint.text.toString()
            val senderName = senderNameInput.text.toString() // Ambil input nama pengirim
            val targetName = targetNameInput.text.toString() // Ambil input nama tujuan

            // Pastikan semua field tidak kosong
            if (message.isNotEmpty() && senderName.isNotEmpty() && targetName.isNotEmpty()) {
                val intent = Intent(this, CardActivity::class.java).apply {
                    putExtra("TARGET_NAME", targetName)
                    putExtra("SENDER_NAME", senderName) // Kirim nama pengirim
                    putExtra("MESSAGE_HINT", message) // Kirim pesan
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setDarkMode(isEnabled: Boolean) {
        // Hanya ubah mode jika ada perbedaan dari pengaturan saat ini
        if (isEnabled != preferences.getBoolean("DARK_MODE", false)) {
            preferences.edit().putBoolean("DARK_MODE", isEnabled).apply()
            AppCompatDelegate.setDefaultNightMode(
                if (isEnabled) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            )
            updateTheme()
        }
    }

    // Mengatur tema toolbar dan background sesuai mode
    private fun updateTheme() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500))
            toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
            nameApp.setTextColor(ContextCompat.getColor(this, R.color.white))
            senderNameInput.setTextColor(ContextCompat.getColor(this, R.color.white))
            senderNameInput.setHintTextColor(ContextCompat.getColor(this, R.color.white))
            targetNameInput.setHintTextColor(ContextCompat.getColor(this, R.color.white))
            targetNameInput.setTextColor(ContextCompat.getColor(this, R.color.white))
            messageHint.setTextColor(ContextCompat.getColor(this, R.color.white))
            messageHint.setHintTextColor(ContextCompat.getColor(this, R.color.white))
            title.setTextColor(ContextCompat.getColor(this, R.color.white))
            mainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.black)) // Latar hitam untuk dark mode
        } else {
            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500))
            toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
            nameApp.setTextColor(ContextCompat.getColor(this, R.color.black))
            mainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.white)) // Latar hitam untuk dark mode
            senderNameInput.setTextColor(ContextCompat.getColor(this, R.color.black))
            senderNameInput.setHintTextColor(ContextCompat.getColor(this, R.color.black))
            targetNameInput.setTextColor(ContextCompat.getColor(this, R.color.black))
            targetNameInput.setHintTextColor(ContextCompat.getColor(this, R.color.black))
            messageHint.setHintTextColor(ContextCompat.getColor(this, R.color.black))
            messageHint.setTextColor(ContextCompat.getColor(this, R.color.black))
            title.setTextColor(ContextCompat.getColor(this, R.color.black))

        }
    }
}
