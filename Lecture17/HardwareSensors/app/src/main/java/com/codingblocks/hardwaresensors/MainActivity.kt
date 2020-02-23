package com.codingblocks.hardwaresensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    lateinit var sensorEventListener: SensorEventListener
    lateinit var sm: SensorManager
    lateinit var proximitySensor: Sensor

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
        sensorEventListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                Log.d("SENSOR", "onAccuracyChanged")
            }

            override fun onSensorChanged(event: SensorEvent?) {
                Log.d(
                    "SENSOR", """onSensorChanged
                    | ${event?.values?.get(0)}
                """.trimMargin()
                )
            }
        }

    }

    override fun onResume() {
        super.onResume()
        sm.registerListener(
            sensorEventListener,
            proximitySensor,
            1000 * 1000
        )
    }

    override fun onPause() {
        sm.unregisterListener(sensorEventListener)
        super.onPause()
    }
}
