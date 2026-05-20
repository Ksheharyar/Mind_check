package com.mskstudios.mindcheck

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Make the video full-screen
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())

        val videoView = findViewById<VideoView>(R.id.videoView)

        // *** IMPORTANT: Change "company_intro" to your video's file name ***
        val videoPath = "android.resource://" + packageName + "/" + R.raw.company_intro
        val uri = Uri.parse(videoPath)
        videoView.setVideoURI(uri)

        // Listen for when the video has finished playing
        videoView.setOnCompletionListener {
            // When the video is done, start the MainActivity (your game)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // Finish this SplashActivity so the user can't go back to it
            finish()
        }

        // Start playing the video
        videoView.start()
    }
}
