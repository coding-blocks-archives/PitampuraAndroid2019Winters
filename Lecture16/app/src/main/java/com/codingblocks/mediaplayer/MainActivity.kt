package com.codingblocks.mediaplayer

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class MainActivity : AppCompatActivity() {
    companion object {
        const val PERM_REQ_CODE_WRITE_STORAGE = 123
        const val PERM_REQ_CODE_READ_STORAGE = 345
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        StrictMode.setThreadPolicy(
//            StrictMode.ThreadPolicy.Builder()
//                .detectAll()
//                .penaltyDeath()
//                .build()
//        )

        btnMediaPlayer.setOnClickListener {
            startActivity(Intent(this, AssetMediaActivity::class.java))
        }


        btnSave.setOnClickListener {

            // Check if we have permission
            val perm = ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )

            if (perm == PackageManager.PERMISSION_GRANTED) {
                // write file
                saveToFile()
            } else {
                // ask for permission

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERM_REQ_CODE_WRITE_STORAGE
                )
            }

        }

        btnRestore.setOnClickListener {
            // Check if we have permission
            val perm = ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )

            if (perm == PackageManager.PERMISSION_GRANTED) {
                // restore file
                restoreFromFile()
            } else {
                // ask for permission

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    PERM_REQ_CODE_READ_STORAGE
                )
            }
        }
    }

    private fun restoreFromFile() {
        Toast.makeText(this, "restoring file", Toast.LENGTH_SHORT)
            .show()

        GlobalScope.launch {
            val dirs = getExternalFilesDirs(Environment.DIRECTORY_DOCUMENTS)

            dirs[dirs.lastIndex]?.let {
                val file = File(it, "data.txt")
                if (file.exists()) {

                    val savedData = file.readText()
                    withContext(Dispatchers.Main) {
                        etData.setText(savedData)
                    }

                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@MainActivity,
                            "Data never saved yet",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }


    }

    private fun saveToFile() {
        Toast.makeText(this, "writing file", Toast.LENGTH_SHORT)
            .show()

        GlobalScope.launch {
            val data = etData.text.toString()
            val dirs = getExternalFilesDirs(Environment.DIRECTORY_DOCUMENTS)

            for (dir in dirs) {
                Log.d("FILEPATH", "writing to " + dir.absolutePath)

                dir?.let {
                    val file = File(dir, "data.txt")
                    file.writeBytes(data.toByteArray())
                    Log.d("FILEPATH", "written = " + (file.exists()).toString())
                }

            }
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERM_REQ_CODE_WRITE_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveToFile()
            } else {
                Toast.makeText(
                    this,
                    "Cannot save without File permission",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        if (requestCode == PERM_REQ_CODE_READ_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                restoreFromFile()
            } else {
                Toast.makeText(
                    this,
                    "Cannot read file due to denied permission",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


}
