package com.example.Video_streaming_app

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View.VISIBLE as VISIBLE

class MainActivity : AppCompatActivity() {
    private var playbackPos=0
    private val url="rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoView.setOnPreparedListener {
            videoView.seekTo(playbackPos)
            videoView.start()
        }

    }

    override fun onStart() {
        super.onStart()
        val uri= Uri.parse(this.url)
        videoView.setVideoURI(uri)
        progressBar2.visibility= VISIBLE
    }

    override fun onPause() {
        super.onPause()
        videoView.pause()
        playbackPos=videoView.currentPosition
    }

    override fun onStop() {
        videoView.stopPlayback()
        super.onStop()
    }
}