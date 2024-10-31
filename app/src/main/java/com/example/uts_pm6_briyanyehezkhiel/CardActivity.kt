package com.example.uts_pm6_briyanyehezkhiel

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SwitchCompat
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager

class CardActivity : AppCompatActivity() {

    private lateinit var senderName: TextView
    private lateinit var messageDetail: TextView
    private lateinit var targetName: TextView
    private lateinit var senderName1: TextView
    private lateinit var messageDetail1: TextView
    private lateinit var targetName1: TextView
    private lateinit var cardImage: ImageView
    private lateinit var darkModeSwitch: SwitchCompat
    private lateinit var toolbar: Toolbar
    private lateinit var nameApp: TextView
    private lateinit var preferences: SharedPreferences
    private lateinit var mainLayout: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        senderName = findViewById(R.id.user_name)
        messageDetail = findViewById(R.id.message_detail)
        targetName = findViewById(R.id.target_name)
        senderName1 = findViewById(R.id.sender_name)
        messageDetail1 = findViewById(R.id.message_detail1)
        targetName1 = findViewById(R.id.target_name_details)
        cardImage = findViewById(R.id.card_image)
//        darkModeSwitch = findViewById(R.id.dark_mode_switch)
        toolbar = findViewById(R.id.toolbar)
        nameApp = findViewById(R.id.nameApp)
        mainLayout = findViewById(R.id.mainlayout)


        preferences = PreferenceManager.getDefaultSharedPreferences(this)

//        val isDarkMode = preferences.getBoolean("DARK_MODE", false)
//        AppCompatDelegate.setDefaultNightMode(
//            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
//        )
//
//        darkModeSwitch.isChecked = isDarkMode
//        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
//            setDarkMode(isChecked)
//        }

        val sender = intent.getStringExtra("SENDER_NAME") ?: getString(R.string.default_message)
        val message = intent.getStringExtra("MESSAGE_HINT") ?: getString(R.string.default_message)
        val target = intent.getStringExtra("TARGET_NAME") ?: getString(R.string.default_message)

        senderName.text = sender
        messageDetail.text = message
        targetName.text = target
        senderName1.text = sender
        messageDetail1.text = message
        targetName1.text = target

        updateTheme()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        updateTheme()
    }

    private fun setDarkMode(isEnabled: Boolean) {
        preferences.edit().putBoolean("DARK_MODE", isEnabled).apply()
        AppCompatDelegate.setDefaultNightMode(
            if (isEnabled) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    private fun updateTheme() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500))
            toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
            nameApp.setTextColor(ContextCompat.getColor(this, R.color.white))
            mainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.black)) // Latar hitam untuk dark mode

        } else {
            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500))
            toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.black))
            nameApp.setTextColor(ContextCompat.getColor(this, R.color.black))
            mainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.white)) // Latar hitam untuk dark mode

        }
    }
}
