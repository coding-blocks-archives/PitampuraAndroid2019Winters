package com.codingblocks.hardwaresensors

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var sensorEventListener: SensorEventListener
    lateinit var sm: SensorManager
    lateinit var proximitySensor: Sensor
    lateinit var accelSensor: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensorList = sm.getSensorList(Sensor.TYPE_ALL)

        for (sensor in sensorList) {
            Log.d(
                "SENSOR", """
                ${sensor.name} 
                ${sensor.vendor}
            """.trimIndent()
            )
        }
        proximitySensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        accelSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorEventListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                Log.d("SENSOR", "onAccuracyChanged")
            }

            override fun onSensorChanged(event: SensorEvent?) {
                event?.values?.let {
                    val bgColor = accelToColor(it[0], it[1], it[2])
                    bgLayout.setBackgroundColor(bgColor)
                }
            }
        }

    }

    private fun accelToColor(ax: Float, ay: Float, az: Float): Int {
        val R = (((ax + 12) / 24) * 255).toInt()
        val G = (((ay + 12) / 24) * 255).toInt()
        val B = (((az + 12) / 24) * 255).toInt()

        return Color.rgb(R, G, B)
    }

    override fun onResume() {
        super.onResume()
        sm.registerListener(
            sensorEventListener,
            accelSensor,
            1000 * 1000
        )
    }

    override fun onPause() {
        sm.unregisterListener(sensorEventListener)
        super.onPause()
    }
}
