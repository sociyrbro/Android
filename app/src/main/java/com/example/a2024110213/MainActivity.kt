package com.example.a2024110213

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 根据系统当前语言更新对应国旗
        updateFlag()

        val textView = findViewById<TextView>(R.id.textView)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            textView.text = getString(R.string.hello_world)
        }
    }

    private fun updateFlag() {
        val imageView = findViewById<ImageView>(R.id.imageView)
        val locale = resources.configuration.locales[0]
        val flagRes = when (locale.language) {
            "zh" -> R.drawable.china
            "ko" -> R.drawable.hanguo
            else -> R.drawable.us
        }
        imageView.setImageResource(flagRes)
    }
}
