package com.codingblocks.layouts

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ll = LinearLayout(this)
        root.addView(ll)
        ll.orientation = LinearLayout.VERTICAL
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            ll.setBackgroundColor(resources.getColor(R.color.colorAccent))
        else
            ll.setBackgroundColor(getColor(R.color.colorAccent))

        for(i in 0..7){
            val innerLL = LinearLayout(this)
            innerLL.orientation = LinearLayout.HORIZONTAL
            ll.addView(innerLL)
            for(j in 0..7){
                val button = Button(this)
                button.text = "Button ${j+1}"
                button.setBackgroundColor(getColor(R.color.colorPrimary))
                button.background = null
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
                )
                params.setMargins()
                button.layoutParams = params
                innerLL.addView(button)
            }
        }

    }
}
