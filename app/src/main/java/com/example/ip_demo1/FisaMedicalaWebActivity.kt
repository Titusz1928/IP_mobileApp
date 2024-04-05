package com.example.ip_demo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import java.io.InputStream

class FisaMedicalaWebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fisa_medicala_web)

        val webview = findViewById<WebView>(R.id.llWebView)

        // Open the HTML file using resources
        val inputStream: InputStream = resources.openRawResource(R.raw.fisa_pacient_pv)

// Read the contents of the HTML file
        val htmlContent = inputStream.bufferedReader().use { it.readText() }

// Display the HTML content (e.g., in a WebView)
        webview.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)

    }
}