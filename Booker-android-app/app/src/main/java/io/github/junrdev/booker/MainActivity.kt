package io.github.junrdev.booker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

        val nextScreen = Intent(this, MainActivity2::class.java)

        findViewById<Button>(R.id.button).setOnClickListener {
            nextScreen.putExtra("action", "companies")
            startActivity(nextScreen)
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            nextScreen.putExtra("action", "routes")
            startActivity(nextScreen)
        }
    }
}