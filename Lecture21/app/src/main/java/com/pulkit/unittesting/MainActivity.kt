package com.pulkit.unittesting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        fun calcFare(dist: Int, time: Int): Int {
            if (dist <= 0 && time <= 0) {
                return 0
            }
            var fare = 50
            if (dist > 5) fare += (dist - 5) * 5
            if (time > 10) fare += ((time - 10) / 5) * 10
            return fare
        }
    }
}

/*
 if dist  <5
 time <10 min
  fare 50
else
1km will be charged at 5 rupees
5 minutes will be charged at 10 rupees
 */
