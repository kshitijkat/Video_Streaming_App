package com.example.Video_streaming_app

import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.widget.MediaController
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View.VISIBLE as VISIBLE

class MainActivity : AppCompatActivity() {
    private var playbackPos=0
    private val url="rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov"
    private lateinit var mediaCon:MediaController
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaCon= MediaController(this)

        videoView.setOnPreparedListener {
            mediaCon.setAnchorView(videoContainer)
            videoView.setMediaController(mediaCon)
            videoView.seekTo(playbackPos)
            videoView.start()
        }
          videoView.setOnInfoListener{player, what, extra ->
              if (what==MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START)
                  progressBar2.visibility= View.INVISIBLE
              true
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