// Make sure this package name matches your project!
package com.mskstudios.mindcheck

import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    inner class WebAppInterface {
        @JavascriptInterface
        fun closeApp() {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Note: The splash screen is now handled by SplashActivity, so the code for it is removed from here.
        setContentView(R.layout.activity_main)

        // The full-screen code has been moved to SplashActivity, so it is also removed from here for simplicity.
        // The theme will handle the full-screen appearance for this activity.

        webView = findViewById(R.id.webview)

        // --- IMPORTANT NEW SETTINGS ---
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true // This line allows localStorage to work.
        webView.settings.databaseEnabled = true // This supports other storage APIs.
        // --- END OF NEW SETTINGS ---

        webView.addJavascriptInterface(WebAppInterface(), "Android")

        webView.loadUrl("file:///android_asset/clue-finder-game.html")
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        // This allows the back button to go back in the game's history if possible.
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
