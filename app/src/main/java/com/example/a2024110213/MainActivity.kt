package com.example.a2024110213

import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // ===== 纯代码构建界面，不使用 XML 布局文件 =====

        // 根布局：垂直线性布局，整体居中
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        // 国旗图片
        val imageView = ImageView(this).apply {
            layoutParams = LinearLayout.LayoutParams(dp(244), dp(159)).apply {
                bottomMargin = dp(24)
            }
        }

        // 问候语文本
        val textView = TextView(this).apply {
            text = getString(R.string.hello_world)
            textSize = 30f // 单位为 sp
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply { bottomMargin = dp(24) }
        }

        // 按钮：点击后文本变为当前语言版本的"我被点击了"
        val button = Button(this).apply {
            text = getString(R.string.button)
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        button.setOnClickListener {
            textView.text = getString(R.string.i_am_clicked)
        }

        root.addView(imageView)
        root.addView(textView)
        root.addView(button)
        setContentView(root)

        // 处理系统栏边距
        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 根据系统当前语言更新对应国旗
        updateFlag(imageView)
    }

    private fun updateFlag(imageView: ImageView) {
        val locale = resources.configuration.locales[0]
        val flagRes = when (locale.language) {
            "zh" -> R.drawable.china
            "ko" -> R.drawable.hanguo
            else -> R.drawable.us
        }
        imageView.setImageResource(flagRes)
    }

    private fun dp(value: Int): Int =
        (value * resources.displayMetrics.density).toInt()
}
