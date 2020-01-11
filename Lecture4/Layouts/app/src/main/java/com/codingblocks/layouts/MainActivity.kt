package com.codingblocks.layouts

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        val buttonArray =
            Array(8) { i ->
                Array(8) { j ->
                    Button(this)
                }
            }

        super.onCreate(savedInstanceState)
        val ll = LinearLayout(this)
        setContentView(ll)
        ll.orientation = LinearLayout.VERTICAL
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            ll.setBackgroundColor(resources.getColor(R.color.colorAccent))
        else
            ll.setBackgroundColor(getColor(R.color.colorAccent))

        for (i in 0..7) {
            val innerLL = LinearLayout(this)
            innerLL.orientation = LinearLayout.HORIZONTAL
            ll.addView(innerLL)
            for (j in 0..7) {
                val button = Button(this)
                buttonArray[i][j] = button
                button.text = "Button ${j + 1}"
                button.setOnClickListener {
                    val sum = i + j
                }
                button.setBackgroundColor(getColor(R.color.colorPrimary))
                button.background = null
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
                )
                button.layoutParams = params
                innerLL.addView(button)
            }
        }


    }
}
