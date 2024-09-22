package io.github.junrdev.booker

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.github.junrdev.booker.domain.presentation.companies.CompaniesScreen
import io.github.junrdev.booker.domain.presentation.routes.RouteScreen

@AndroidEntryPoint
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val fragment = when (intent.getStringExtra("action")) {
            "companies" -> CompaniesScreen()
            "routes" -> RouteScreen()
            else -> CompaniesScreen()
        }

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainFragment, fragment)
        }.commit()
    }
}