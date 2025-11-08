package com.vtower.acceptafk.activity

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.vtower.acceptafk.R
import com.vtower.acceptafk.fragment.ConfigFragment
import com.vtower.acceptafk.fragment.HomeFragment
import com.vtower.acceptafk.services.AcceptServerWebSocket
import java.sql.Timestamp

class MainActivity : AppCompatActivity() {

    private lateinit var homeButton: Button
    private lateinit var configButton: Button

    private lateinit var fragmentHome: HomeFragment
    private lateinit var fragmentConfig: ConfigFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        fragmentHome = HomeFragment()
        fragmentConfig = ConfigFragment()
//        supportFragmentManager.beginTransaction().replace(R.id.frameContent, fragmentHome).commit()

        homeButton = findViewById(R.id.homeButton)

        homeButton.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.frameContent, fragmentHome).commit()
        }

        configButton = findViewById(R.id.configButton)

        configButton.setOnClickListener {
            enviarMensagem()
            supportFragmentManager.beginTransaction().replace(R.id.frameContent, fragmentConfig).commit()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()

        try {
            AcceptServerWebSocket.startConnection("10.0.2.2")
        }
        catch (e: Exception)
        {
            println("################### Erro ao iniciar conexão  ${e.message}")
        }
    }

    fun enviarMensagem() {
        AcceptServerWebSocket.sendMessage("Pedro", "Olá servidor!")
    }


}