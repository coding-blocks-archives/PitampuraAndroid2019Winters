package com.codingblocks.mediaplayer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_media_player.*

class MediaPlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)

        videoView.setMediaController(MediaController(this))
//        val resId = resources.getIdentifier("video", "raw", packageName)
//        val path = "android.resource://$packageName/$resId"
        val path = "android.resource://$packageName/${R.raw.video}"
        videoView.setVideoURI(Uri.parse(path))
    }
}
