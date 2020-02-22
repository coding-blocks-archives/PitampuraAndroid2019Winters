package com.codingblocks.mediaplayer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_asset_media.*
import kotlinx.android.synthetic.main.activity_media_player.*
import kotlinx.android.synthetic.main.activity_media_player.videoView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class AssetMediaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asset_media)

        videoView.setMediaController(MediaController(this))
        copyFileFromAssets()

    }


    private fun copyFileFromAssets() {

        GlobalScope.launch {
            val finStream = assets.open("video.mp4")
            val dirs = getExternalFilesDirs(Environment.DIRECTORY_MOVIES)
            val videoFile = dirs[dirs.lastIndex]?.let { File(it, "video.mp4") }

            videoFile?.let {
                it.writeBytes(finStream.readBytes())
                videoView.setVideoURI(Uri.parse(videoFile.absolutePath))

                Log.d("FILE", "written to " + videoFile.absoluteFile)
            }

            withContext(Dispatchers.Main) {
                videoFile?.let {
                    videoView.start()
                }
            }
        }

    }
}
