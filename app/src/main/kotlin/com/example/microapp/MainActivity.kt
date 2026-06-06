package com.example.microapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Status bar & nav bar colors matching app background (#07071A)
        window.statusBarColor = android.graphics.Color.parseColor("#07071A")
        window.navigationBarColor = android.graphics.Color.parseColor("#07071A")

        setContent {
            InstaVaultApp()
        }
    }
}
