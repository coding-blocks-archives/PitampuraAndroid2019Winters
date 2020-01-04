package com.codingblocks.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(view: View) {
        when (view.id) {
            R.id.button -> {
//                updateValue(row = 0, col = 0, player = PLAYER)
            }
            R.id.button2 -> {
//                updateValue(row = 0, col = 1, player = PLAYER)

            }
            R.id.button3 -> {
//                updateValue(row = 0, col = 2, player = PLAYER)

            }
            R.id.button4 -> {
//                updateValue(row = 1, col = 0, player = PLAYER)

            }
            R.id.button5 -> {
//                updateValue(row = 1, col = 1, player = PLAYER)

            }
            R.id.button6 -> {
//                updateValue(row = 1, col = 2, player = PLAYER)

            }
            R.id.button7 -> {
//                updateValue(row = 2, col = 0, player = PLAYER)

            }
            R.id.button8 -> {
//                updateValue(row = 2, col = 1, player = PLAYER)

            }
            R.id.button9 -> {
//                updateValue(row = 2, col = 2, player = PLAYER)

            }
        }    }

    var boardStatus = Array(3) { IntArray(3) }
    lateinit var baord:Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        baord = arrayOf(
            arrayOf(button,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )

        for( i in baord){
            for(button in i){
                button.setOnClickListener(this)
            }
        }
    }
}
