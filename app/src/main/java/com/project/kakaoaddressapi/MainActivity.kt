package com.project.kakaoaddressapi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var et_address: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et_address = findViewById(R.id.et_address)
        val btn_search = findViewById<Button>(R.id.button)

        btn_search.setOnClickListener {
            val intent = Intent(this@MainActivity, WebViewActivity::class.java)
            getSearchResult.launch(intent)
        }
    }

    private val getSearchResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { results ->
            if(results?.resultCode == RESULT_OK) {
                if(results?.data != null) {
                    val data = results?.data!!.getStringExtra("data")
                    et_address?.setText(data)
                }
            }
        }
}