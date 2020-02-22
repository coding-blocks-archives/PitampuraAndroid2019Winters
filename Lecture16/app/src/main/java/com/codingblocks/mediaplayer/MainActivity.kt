package com.codingblocks.mediaplayer

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val PERM_REQ_CODE_STORAGE = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                    PERM_REQ_CODE_STORAGE
                )
            }

        }
    }

    private fun saveToFile() {
        Toast.makeText(this, "writing file", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERM_REQ_CODE_STORAGE) {
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

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


}
