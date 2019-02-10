package me.gilo.starter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import me.gilo.starter.ui.home.HomeActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(baseContext, HomeActivity::class.java))
    }
}
